package com.berkay.feature.detail.presentation.screens

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
import com.berkay.ui.components.getirbutton.GetirButton
import com.berkay.ui.components.horizontalcartcontrolbutton.HorizontalCardControlButton
import com.berkay.ui.theme.LocalColorScheme

@Composable
fun ProductDetailBottomBar(
    productCount: Int,
    onAddProductClick: () -> Unit,
    onRemoveProductClick: () -> Unit
) {

    val localColorScheme = LocalColorScheme.current
    val shouldShowCartControlButton: Boolean = productCount > 0

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
        if (shouldShowCartControlButton.not()){
            GetirButton(
                text = "Sepete Ekle"
            ) {
                onAddProductClick()
            }
        }else {
            HorizontalCardControlButton(
                count = productCount,
                onAddClick = onAddProductClick,
                onRemoveClick = onRemoveProductClick
            )
        }
    }

}
