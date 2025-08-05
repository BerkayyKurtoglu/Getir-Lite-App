package com.berkay.feature.count.presentation

sealed interface CartAction {
    data object OnBackClicked : CartAction
    data object OnRemoveAllClicked : CartAction
    data object OnCheckoutButtonClicked : CartAction

    data object OnConfirmCheckoutDialog : CartAction
    data object OnDismissCheckoutDialog : CartAction

    data class AddProduct(val product: CartProduct) : CartAction
    data class RemoveProduct(val product: CartProduct) : CartAction
    data class OnProductClick(val product: CartProduct) : CartAction
}

