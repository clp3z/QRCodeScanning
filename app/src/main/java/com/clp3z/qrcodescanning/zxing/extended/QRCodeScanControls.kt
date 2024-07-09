package com.clp3z.qrcodescanning.zxing.extended

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.FlashlightOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun QRCodeScanControls(
    onCloseButtonClick: () -> Unit,
    onFlashlightClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 18.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = { onCloseButtonClick() },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.5f))
                        //.background(Color(color = 0xFF303030).copy(alpha = 0.9f))
                        .size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        //tint = Color.White,
                        tint = Color.Black,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Scan the QR Code", style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
            }
        }
        Box(modifier = Modifier.size(300.dp)) {
            ScanningAreaOutline(
                width = 306.dp,
                height = 306.dp,
                strokeWidth = 6.dp,
                borderColor = Color(0xFFFEE021)
            )
        }
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            IconButton(
                onClick = { onFlashlightClick() },
                modifier = Modifier
                    .clip(CircleShape)
                    //.background(Color(color = 0xFF303030).copy(alpha = 0.9f))
                    .background(Color.White.copy(alpha = 0.5f))
                    .size(56.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.FlashlightOn,
                    // tint = Color.White,
                    tint = Color.Black,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun QRCodeScanControlsPreview() {
    QRCodeScanControls(
        onCloseButtonClick = {},
        onFlashlightClick = {}
    )
}
