package com.berkay.feature.detail.presentation

sealed interface ProductDetailEffect {
    data object NavigateBack : ProductDetailEffect
    data object NavigateToCart : ProductDetailEffect
    data class ShowErrorMessage(val errorMessage: String) : ProductDetailEffect
}
