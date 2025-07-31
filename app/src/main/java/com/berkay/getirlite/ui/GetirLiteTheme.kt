package com.berkay.getirlite.ui

import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.LocalActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import com.berkay.ui.theme.LocalColorScheme
import com.berkay.ui.theme.MainColorScheme

@Composable
internal fun GetirLiteTheme(
    content: @Composable () -> Unit
) {
    val context = LocalActivity.current as? ComponentActivity
    val colorScheme = MainColorScheme

    SideEffect {
        context?.let {
            context.enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.dark(colorScheme.corePrimaryColor.toArgb())
            )
        }
    }

    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        content = content
    )
}
