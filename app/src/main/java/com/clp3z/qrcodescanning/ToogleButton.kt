package com.clp3z.qrcodescanning

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Selector(
    selectedItem: String,
    options: List<String>,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEach {
                val isSelected = it == selectedItem
                Text(
                    text = it,
                    color = when (isSelected) {
                        true -> Color.Yellow
                        else -> Color.White
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = when (isSelected) {
                        true -> FontWeight.W600
                        else -> FontWeight.W400
                    },
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clickable { onSelected(it) }
                        .weight(1f)
                        .clip(CircleShape)
                        .background(
                            when (isSelected) {
                                true -> MaterialTheme.colorScheme.primary
                                false -> MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                            }
                        )
                        .padding(8.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun ToggleButtonPreview() {
    Selector(
        selectedItem = "Option 1",
        options = listOf("Option 1", "Option 2"),
        onSelected = { /*TODO*/ }
    )
}

