package com.berkay.feature.detail.presentation

sealed interface ProductDetailAction {

    data object OnBackClick : ProductDetailAction
    data object AddProduct : ProductDetailAction
    data object RemoveProduct : ProductDetailAction
    data object OnCartClick : ProductDetailAction

}
