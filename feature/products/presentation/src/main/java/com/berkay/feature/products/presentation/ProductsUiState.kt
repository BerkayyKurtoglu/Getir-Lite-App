package com.berkay.feature.products.presentation

import com.berkay.presentation.formatAsPriceTr
import com.berkay.ui.components.productcard.ProductUiModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class ProductsReadyState(
    val screenState: ProductsUiState = ProductsUiState(),
    val errorDialogState: ErrorDialogState = ErrorDialogState(),
    val isLoading: Boolean = false,
    val errorState: ErrorState? = null
){
    val showErrorDialog: Boolean
        get() = errorState != null
}

data class ProductsUiState(
    val verticalProductUiModels: PersistentList<ProductUiModel> = persistentListOf(),
    val horizontalProductUiModels: PersistentList<ProductUiModel> = persistentListOf(),
    val cartPrice: Double = 0.00,
    val title: String = ""
){
    val cartPriceString: String
        get() = cartPrice.formatAsPriceTr()

    val showCartTotalButton: Boolean
        get() = cartPrice > 0.00
}

data class ErrorDialogState(
    val title: String = "",
    val confirmText: String = ""
)

data class ErrorState(
    val message: String = ""
)

