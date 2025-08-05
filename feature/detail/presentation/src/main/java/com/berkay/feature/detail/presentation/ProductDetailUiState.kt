package com.berkay.feature.detail.presentation

import com.berkay.presentation.formatAsPriceTr

data class ProductDetailUiState(
    val title: String,
    val id: String = "",
    val name: String = "",
    val price: Double = 0.00,
    val cartPrice: Double = 0.00,
    val attribute: String = "",
    val imageUrl: String = "",
    val count: Int = 0
){
    val cartPriceString: String
        get() {
            return cartPrice.formatAsPriceTr()
        }
    val showCartTotalButton: Boolean
        get() = cartPrice > 0
}