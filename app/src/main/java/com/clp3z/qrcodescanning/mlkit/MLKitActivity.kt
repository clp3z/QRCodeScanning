package com.clp3z.qrcodescanning.mlkit

import android.Manifest
import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.clp3z.qrcodescanning.ui.theme.QRCodeScanningTheme

class MLKitActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QRCodeScanningTheme {
                var isPermissionGranted by remember { mutableStateOf(false) }
                val application = LocalContext.current.applicationContext as Application

                if (isCameraPermissionGranted(application)) {
                    isPermissionGranted = true
                } else {
                    RequestPermission(
                        permission = Manifest.permission.CAMERA,
                        onPermissionResult = { isPermissionGranted = it }
                    )
                }

                if (isPermissionGranted) CameraView(
                    onUrlResult = { scannedUrl ->
                        val resultIntent = Intent().putExtra("url", scannedUrl)
                        setResult(RESULT_OK, resultIntent)
                        finish()
                    }
                )
            }
        }
    }
}