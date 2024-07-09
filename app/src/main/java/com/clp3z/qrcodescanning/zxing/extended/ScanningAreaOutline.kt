package com.clp3z.qrcodescanning.zxing.extended

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ScanningAreaOutline(
    width: Dp,
    height: Dp,
    strokeWidth: Dp,
    borderColor: Color
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val scanAreaLeft = (canvasWidth - width.toPx()) / 2
        val scanAreaTop = (canvasHeight - height.toPx()) / 2

        val scanArea = Rect(
            left = scanAreaLeft,
            top = scanAreaTop,
            right = scanAreaLeft + width.toPx(),
            bottom = scanAreaTop + height.toPx()
        )

        val cornerLength = width / 8

        drawRoundRect(
            color = borderColor,
            topLeft = Offset(scanArea.left, scanArea.top),
            size = Size(cornerLength.toPx(), strokeWidth.toPx()),
            cornerRadius = CornerRadius(8.dp.toPx(), 8.dp.toPx())
        )
        drawRoundRect(
            borderColor,
            topLeft = Offset(scanArea.left, scanArea.top),
            size = Size(strokeWidth.toPx(), cornerLength.toPx()),
            cornerRadius = CornerRadius(8.dp.toPx(), 8.dp.toPx())
        )

        drawRoundRect(
            borderColor,
            topLeft = Offset(scanArea.right - cornerLength.toPx(), scanArea.top),
            size = Size(cornerLength.toPx(), strokeWidth.toPx()),
            cornerRadius = CornerRadius(8.dp.toPx(), 8.dp.toPx())
        )
        drawRoundRect(
            borderColor,
            topLeft = Offset(scanArea.right - strokeWidth.toPx(), scanArea.top),
            size = Size(strokeWidth.toPx(), cornerLength.toPx()),
            cornerRadius = CornerRadius(8.dp.toPx(), 8.dp.toPx())
        )

        drawRoundRect(
            borderColor,
            topLeft = Offset(scanArea.left, scanArea.bottom - strokeWidth.toPx()),
            size = Size(cornerLength.toPx(), strokeWidth.toPx()),
            cornerRadius = CornerRadius(8.dp.toPx(), 8.dp.toPx())
        )
        drawRoundRect(
            borderColor,
            topLeft = Offset(scanArea.left, scanArea.bottom - cornerLength.toPx()),
            size = Size(strokeWidth.toPx(), cornerLength.toPx()),
            cornerRadius = CornerRadius(8.dp.toPx(), 8.dp.toPx())
        )

        drawRoundRect(
            borderColor,
            topLeft = Offset(scanArea.right - cornerLength.toPx(), scanArea.bottom - strokeWidth.toPx()),
            size = Size(cornerLength.toPx(), strokeWidth.toPx()),
            cornerRadius = CornerRadius(8.dp.toPx(), 8.dp.toPx())
        )
        drawRoundRect(
            borderColor,
            topLeft = Offset(scanArea.right - strokeWidth.toPx(), scanArea.bottom - cornerLength.toPx()),
            size = Size(strokeWidth.toPx(), cornerLength.toPx()),
            cornerRadius = CornerRadius(8.dp.toPx(), 8.dp.toPx())
        )
    }
}

@Preview
@Composable
fun ScanningAreaOutlinePreview() {
    ScanningAreaOutline(
        width = 300.dp,
        height = 300.dp,
        strokeWidth = 6.dp,
        borderColor = Color.Red
    )
}
