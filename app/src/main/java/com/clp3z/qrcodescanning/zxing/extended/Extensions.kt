package com.clp3z.qrcodescanning.zxing.extended

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T : Any> LifecycleOwner.launchAndCollect(
    flow: Flow<T>,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    action: (T) -> Unit
) {
    lifecycleScope.launch {
        repeatOnLifecycle(state) {
            flow.collect { action(it) }
        }
    }
}