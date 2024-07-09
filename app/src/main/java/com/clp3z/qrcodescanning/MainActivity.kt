package com.clp3z.qrcodescanning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.clp3z.qrcodescanning.ui.theme.QRCodeScanningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QRCodeScanningTheme {
                MainScreen()
            }
        }
    }
}

