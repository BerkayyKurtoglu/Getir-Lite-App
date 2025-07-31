package com.berkay.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class GetirLiteColorScheme(
    val mainGetirWhite: Color = Color.Unspecified,
    val corePrimaryColor: Color = Color.Unspecified,
    val corePrimaryContainerColor: Color = Color.Unspecified,
    val textDarkColor: Color = Color.Unspecified,
    val textSecondaryColor: Color = Color.Unspecified,
    val textLightColor: Color = Color.Unspecified
)
