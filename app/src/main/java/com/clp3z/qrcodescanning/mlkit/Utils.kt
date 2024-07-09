package com.clp3z.qrcodescanning.mlkit

import androidx.compose.ui.geometry.Rect

fun Rect.isInside(outer: Rect): Boolean =
    /*outer.left <= this.left &&
    outer.top <= this.top &&
    outer.right >= this.right &&
    outer.bottom >= this.bottom*/
    left >= outer.left &&
    right <= outer.right &&
    top >= outer.top &&
    bottom <= outer.bottom
