package com.clp3z.qrcodescanning.zxing

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import com.clp3z.qrcodescanning.R
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.journeyapps.barcodescanner.ViewfinderView

class CustomCaptureActivity : Activity(), DecoratedBarcodeView.TorchListener {
    private lateinit var capture: CaptureManager
    private lateinit var barcodeScannerView: DecoratedBarcodeView
    private lateinit var switchFlashlightButton: Button
    private lateinit var viewfinderView: ViewfinderView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_scanner)

        barcodeScannerView = findViewById(R.id.zxing_barcode_scanner)
        barcodeScannerView.setTorchListener(this)

        // switchFlashlightButton = findViewById(R.id.switch_flashlight)
        viewfinderView = barcodeScannerView.findViewById(R.id.zxing_viewfinder_view)

        if (!hasFlash()) switchFlashlightButton.visibility = View.GONE

        capture = CaptureManager(this, barcodeScannerView)
        capture.initializeFromIntent(intent, savedInstanceState)
        capture.setShowMissingCameraPermissionDialog(false)
        capture.decode()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return barcodeScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event)
    }

    private fun hasFlash(): Boolean {
        return applicationContext.packageManager
            .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
    }

    fun switchFlashlight() {
        if (getString(R.string.turn_on_flashlight) == switchFlashlightButton.text) {
            barcodeScannerView.setTorchOn()
        } else {
            barcodeScannerView.setTorchOff()
        }
    }

    override fun onTorchOn() {
        switchFlashlightButton.setText(R.string.turn_off_flashlight)
    }

    override fun onTorchOff() {
        switchFlashlightButton.setText(R.string.turn_on_flashlight)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        capture.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        capture.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()
        capture.onResume()
    }

    override fun onPause() {
        super.onPause()
        capture.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        capture.onDestroy()
    }
}
