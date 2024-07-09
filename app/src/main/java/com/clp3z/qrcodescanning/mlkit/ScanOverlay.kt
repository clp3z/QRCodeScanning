package com.clp3z.qrcodescanning.mlkit

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ScanningAreaOutline(
    scanningArea: ScanningArea,
    borderColor: Color
) {
    val strokeWidth = 6.dp

    Canvas(modifier = Modifier.fillMaxSize()) {
        val scanArea = scanningArea.getRectArea(size.width, size.height)
        val cornerLength = size.width / 9
        val radiusPx = 8.dp.toPx()

        drawRoundRect(
            color = borderColor,
            topLeft = Offset(scanArea.left, scanArea.top),
            size = Size(cornerLength, strokeWidth.toPx()),
            cornerRadius = CornerRadius(radiusPx, radiusPx)
        )
        drawRoundRect(
            borderColor,
            topLeft = Offset(scanArea.left, scanArea.top),
            size = Size(strokeWidth.toPx(), cornerLength),
            cornerRadius = CornerRadius(radiusPx, radiusPx)
        )

        drawRoundRect(
            borderColor,
            topLeft = Offset(scanArea.right - cornerLength, scanArea.top),
            size = Size(cornerLength, strokeWidth.toPx()),
            cornerRadius = CornerRadius(radiusPx, radiusPx)
        )
        drawRoundRect(
            borderColor,
            topLeft = Offset(scanArea.right - strokeWidth.toPx(), scanArea.top),
            size = Size(strokeWidth.toPx(), cornerLength),
            cornerRadius = CornerRadius(radiusPx, radiusPx)
        )

        drawRoundRect(
            borderColor,
            topLeft = Offset(scanArea.left, scanArea.bottom - strokeWidth.toPx()),
            size = Size(cornerLength, strokeWidth.toPx()),
            cornerRadius = CornerRadius(radiusPx, radiusPx)
        )
        drawRoundRect(
            borderColor,
            topLeft = Offset(scanArea.left, scanArea.bottom - cornerLength),
            size = Size(strokeWidth.toPx(), cornerLength),
            cornerRadius = CornerRadius(radiusPx, radiusPx)
        )

        drawRoundRect(
            borderColor,
            topLeft = Offset(
                x = scanArea.right - cornerLength,
                y = scanArea.bottom - strokeWidth.toPx()
            ),
            size = Size(cornerLength, strokeWidth.toPx()),
            cornerRadius = CornerRadius(radiusPx, radiusPx)
        )
        drawRoundRect(
            borderColor,
            topLeft = Offset(
                x = scanArea.right - strokeWidth.toPx(),
                y = scanArea.bottom - cornerLength
            ),
            size = Size(strokeWidth.toPx(), cornerLength),
            cornerRadius = CornerRadius(radiusPx, radiusPx)
        )
    }
}

@Composable
fun MaskOverlay(
    scanningArea: ScanningArea,
    maskColor: Color
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val scanArea = scanningArea.getRectArea(size.width, size.height)
        val maskStrokeWidth = 5.dp.toPx()

        drawRect(
            maskColor,
            topLeft = Offset(0f, 0f),
            size = Size(size.width, scanArea.top + maskStrokeWidth)
        )

        drawRect(
            maskColor,
            topLeft = Offset(0f, scanArea.bottom - maskStrokeWidth),
            size = Size(size.width, size.height - maskStrokeWidth)
        )

        drawRect(
            maskColor,
            topLeft = Offset(0f, scanArea.top + maskStrokeWidth),
            size = Size(
                width = scanArea.left + maskStrokeWidth,
                height = scanArea.height - 2 * maskStrokeWidth
            )
        )

        drawRect(
            maskColor,
            topLeft = Offset(
                x = scanArea.right - maskStrokeWidth,
                y = scanArea.top + maskStrokeWidth
            ),
            size = Size(
                width = size.width + scanArea.right,
                height = scanArea.height - 2 * maskStrokeWidth
            )
        )
    }
}

@Composable
fun ScanOverlay(
    modifier: Modifier = Modifier,
    scanningArea: ScanningArea,
    maskColor: Color,
    borderColor: Color
) {
    Box(modifier = modifier.fillMaxSize()) {

        MaskOverlay(
            scanningArea = scanningArea,
            maskColor = maskColor
        )

        ScanningAreaOutline(
            scanningArea = scanningArea,
            borderColor = borderColor
        )
    }
}

@Preview
@Composable
fun ScanningOverlayPreview() {
    ScanOverlay(
        modifier = Modifier,
        scanningArea = ScanningArea(percentage = 0.7f),
        maskColor = Color.Black.copy(alpha = 0.5f),
        borderColor = Color.Yellow
    )
}