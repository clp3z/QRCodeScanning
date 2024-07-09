package com.clp3z.qrcodescanning.zxing.extended

import android.os.Bundle
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView

class QRCodeScanActivity : ExtendedCaptureActivity() {

    private val viewModel : QRCodeScanViewModel by lazy { QRCodeScanViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val composeView = ComposeView(this).apply {
            setContent {
                QRCodeScanControls(
                    onFlashlightClick = viewModel::onFlashlightClick,
                    onCloseButtonClick = viewModel::onCloseButtonClick
                )
            }
        }

        addContentView(
            composeView,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )

        launchAndCollect(viewModel.viewState) {
            processViewState(it)
        }
    }

    private fun processViewState(viewState: QRCodeScanViewModel.ViewState) {
        when {
            viewState.wasCloseButtonPressed -> finish()
            viewState.isFlashlightOn -> barcodeScannerView.setTorchOn()
            !viewState.isFlashlightOn -> barcodeScannerView.setTorchOff()
        }
    }
}
