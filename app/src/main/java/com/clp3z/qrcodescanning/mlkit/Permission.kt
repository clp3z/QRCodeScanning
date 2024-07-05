package com.clp3z.qrcodescanning.mlkit

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.core.content.ContextCompat.checkSelfPermission

@Composable
fun RequestPermission(permission: String, onPermissionResult: (Boolean) -> Unit) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = onPermissionResult
    )

    LaunchedEffect(Unit) {
        launcher.launch(permission)
    }
}

 fun isCameraPermissionGranted(application: Application) =
     checkSelfPermission(application, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
