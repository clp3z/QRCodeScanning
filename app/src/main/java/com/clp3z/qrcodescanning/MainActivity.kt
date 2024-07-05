package com.clp3z.qrcodescanning

import android.Manifest
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clp3z.qrcodescanning.mlkit.CameraScreen
import com.clp3z.qrcodescanning.mlkit.RequestPermission
import com.clp3z.qrcodescanning.mlkit.isCameraPermissionGranted
import com.clp3z.qrcodescanning.ui.theme.QRCodeScanningTheme
import com.clp3z.qrcodescanning.zxing.QRCodeScanScreen
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import com.journeyapps.barcodescanner.ScanOptions.QR_CODE

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var isPermissionGranted by remember { mutableStateOf(false) }

            QRCodeScanningTheme {
                // QRCodeScanScreen()

                val application = LocalContext.current.applicationContext as Application
                if (isCameraPermissionGranted(application)) {
                    isPermissionGranted = true
                } else {
                    RequestPermission(
                        permission = Manifest.permission.CAMERA,
                        onPermissionResult = { isPermissionGranted = it }
                    )
                }

                if (isPermissionGranted) CameraScreen()
            }
        }
    }
}

