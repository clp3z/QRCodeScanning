package com.clp3z.qrcodescanning.zxing.extended

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QRCodeScanViewModel : ViewModel() {

    data class ViewState(
        val isFlashlightOn: Boolean = false,
        val wasCloseButtonPressed: Boolean = false
    )

    private val _viewState = MutableStateFlow(ViewState())
    val viewState: StateFlow<ViewState> = _viewState.asStateFlow()

    fun onFlashlightClick() {
        _viewState.update { it.copy(isFlashlightOn = !it.isFlashlightOn) }
    }

    fun onCloseButtonClick() {
        _viewState.update { it.copy(wasCloseButtonPressed = true) }
    }
}
