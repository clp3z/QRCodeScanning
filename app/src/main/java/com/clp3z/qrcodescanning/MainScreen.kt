package com.clp3z.qrcodescanning

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clp3z.qrcodescanning.mlkit.MLKitActivity
import com.clp3z.qrcodescanning.ui.theme.QRCodeScanningTheme
import com.clp3z.qrcodescanning.zxing.CustomCaptureActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@Composable
fun MainScreen() {
    var url by remember { mutableStateOf("None") }
    var librarySelected by remember { mutableStateOf(Libraries.Zxing) }
    val context = LocalContext.current

    val scanLauncher = rememberLauncherForActivityResult(
        contract = ScanContract(),
        onResult = { url = it.contents ?: "Error." }
    )

    val mlkitLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            url = result.data?.getStringExtra("url") ?: ""
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Selector(
            selectedItem = librarySelected.name,
            options = Libraries.entries.map { it.name },
            onSelected = {
                librarySelected = Libraries.valueOf(it)
            }
        )

        Button(
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary),
            onClick = {
                if (librarySelected == Libraries.Zxing) {
                    val scanOptions = ScanOptions().apply {
                        setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                        setCaptureActivity(CustomCaptureActivity::class.java)
                        setOrientationLocked(false)
                        setBeepEnabled(false)
                    }
                    scanLauncher.launch(scanOptions)
                } else {
                    val intent = Intent(context, MLKitActivity::class.java)
                    mlkitLauncher.launch(intent)
                }
            },
        ) {
            Text(text = "Scan QR Code")
        }

        Text(
            text = "Url: $url",
            color = Color.White,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

enum class Libraries {
    Zxing,
    MLKit
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainScreenPreview() {
    QRCodeScanningTheme {
        MainScreen()
    }
}
