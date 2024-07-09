package com.clp3z.qrcodescanning.zxing.extended

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import com.clp3z.qrcodescanning.R
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.journeyapps.barcodescanner.ViewfinderView

open class ExtendedCaptureActivity : AppCompatActivity() {

    private lateinit var capture: CaptureManager
    private lateinit var viewfinderView: ViewfinderView
    protected lateinit var barcodeScannerView: DecoratedBarcodeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_scanner)

        initializeViews()
        initializeCaptureManager(savedInstanceState)
    }

    private fun initializeViews() {
        barcodeScannerView = findViewById(R.id.zxing_barcode_scanner)
        viewfinderView = barcodeScannerView.findViewById(R.id.zxing_viewfinder_view)
    }

    private fun initializeCaptureManager(savedInstanceState: Bundle?) {
        capture = CaptureManager(this, barcodeScannerView)
        capture.initializeFromIntent(intent, savedInstanceState)
        capture.setShowMissingCameraPermissionDialog(false)
        capture.decode()
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        capture.onSaveInstanceState(outState)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        capture.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return barcodeScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event)
    }
}
