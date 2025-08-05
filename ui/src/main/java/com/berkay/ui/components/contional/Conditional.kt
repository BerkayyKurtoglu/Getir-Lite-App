package com.berkay.ui.components.contional

import androidx.compose.runtime.Composable

@Composable
fun Conditional(predicate: () -> Boolean, content: @Composable () -> Unit) {
    if (predicate()) {
        content()
    }
}
