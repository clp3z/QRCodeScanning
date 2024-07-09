package com.clp3z.qrcodescanning.mlkit

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.compose.ui.geometry.Offset
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

class BarcodeAnalyzer(
    private val scanningArea: ScanningArea,
    private val onUrlResult: (String) -> Unit
) : ImageAnalysis.Analyzer {

    private val options = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
        .build()

    private val scanner = BarcodeScanning.getClient(options)

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        imageProxy.image?.let { image ->

            val rotatedImage = InputImage.fromMediaImage(
                image,
                imageProxy.imageInfo.rotationDegrees
            )

            val scanArea = scanningArea.getRectArea(
                width = rotatedImage.width.toFloat(),
                height = rotatedImage.height.toFloat()
            )
            Log.d("BarcodeAnalyzer", "scanArea: $scanArea")

            scanner.process(rotatedImage)
                .addOnSuccessListener { barcodes ->
                    barcodes
                        .filter { barcode ->
                            barcode.boundingBox?.let { box ->
                                scanArea.contains(
                                    Offset(
                                        x = box.centerX().toFloat(),
                                        y = box.centerY().toFloat()
                                    )
                                )
                                // Log.d("BarcodeAnalyzer", "box: ${box.toComposeRect()}")
                                // box.toComposeRect().isInside(scanArea)
                            } ?: false
                        }
                        .mapNotNull { it.rawValue }
                        .joinToString(",")
                        .takeIf { it.isNotEmpty() }
                        ?.let { onUrlResult(it) }
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        }
    }
}
