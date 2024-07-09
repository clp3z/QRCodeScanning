package com.clp3z.qrcodescanning.mlkit

import androidx.compose.ui.geometry.Rect

data class ScanningArea(val percentage: Float) {
    init {
        require(percentage in 0f..1f) { "scanAreaPercentage must be between 0 and 1" }
    }

    fun getRectArea(width: Float, height: Float): Rect {
        val scanAreaSize = minOf(width, height) * percentage
        return Rect(
            left = (width - scanAreaSize) / 2,
            top = (height - scanAreaSize) / 2,
            right = (width + scanAreaSize) / 2,
            bottom = (height + scanAreaSize) / 2
        )
    }
}
