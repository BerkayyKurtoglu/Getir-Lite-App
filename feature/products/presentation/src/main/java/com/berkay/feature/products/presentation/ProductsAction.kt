package com.berkay.feature.products.presentation

import com.berkay.ui.components.productcard.ProductUiModel

sealed interface ProductsAction {

    data object OnCartClick : ProductsAction
    data object OnConfirmErrorDialog : ProductsAction
    data class OnProductClick(val product: ProductUiModel) : ProductsAction
    data class OnAddVerticalProduct(val product: ProductUiModel) : ProductsAction
    data class OnRemoveVerticalProduct(val product: ProductUiModel) : ProductsAction
    data class OnAddHorizontalProduct(val product: ProductUiModel) : ProductsAction
    data class OnRemoveHorizontalProduct(val product: ProductUiModel) : ProductsAction

}
