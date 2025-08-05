package com.berkay.ui.components.productcard

import androidx.compose.runtime.Immutable

@Immutable
data class ProductUiModel(
    val id: String,
    val name: String,
    val attribute: String,
    val price: Double,
    val priceText: String,
    val imageUrl: String,
    val count: Int
){
    val shouldShowAttribute: Boolean
        get() {
            return attribute.isNotEmpty()
        }
}
