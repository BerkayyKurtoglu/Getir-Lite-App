package com.berkay.feature.count.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.berkay.ui.components.checkoutbutton.CheckoutButton
import com.berkay.ui.theme.LocalColorScheme

@Composable
fun CartBottomBar(
    priceText: String,
    buttonText: String,
    onClick: () -> Unit,
) {

    val localColorScheme = LocalColorScheme.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .shadow(
                elevation = 6.dp,
                clip = false
            )
            .background(localColorScheme.mainGetirWhite)
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        CheckoutButton(
            buttonText = buttonText,
            priceText = priceText,
            onClick = onClick
        )
    }

}
