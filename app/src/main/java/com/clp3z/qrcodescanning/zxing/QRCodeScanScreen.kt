package com.clp3z.qrcodescanning.zxing

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clp3z.qrcodescanning.ui.theme.QRCodeScanningTheme
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@Composable
fun QRCodeScanScreen() {
    var url by remember { mutableStateOf("") }
    val scanLauncher = rememberLauncherForActivityResult(
        contract = ScanContract(),
        onResult = { url = it.contents ?: "Error." }
    )
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                val scanOptions = ScanOptions().apply {
                    setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                    setCaptureActivity(CustomCaptureActivity::class.java)
                    setOrientationLocked(false)
                    setBeepEnabled(false)
                }
                scanLauncher.launch(scanOptions)
            }
        ) {
            Text(text = "Scan QR Code")
        }
        Text(text = url)
    }
}

@Preview(showBackground = true)
@Composable
fun ZxingQRCodeScanScreenPreview() {
    QRCodeScanningTheme {
        QRCodeScanScreen()
    }
}