package com.berkay.feature.count.presentation

import androidx.compose.runtime.Immutable
import com.berkay.presentation.formatAsPriceTr
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class CartUiState(
    val title: String = "",
    val showCheckoutDialog: Boolean = false,
    val confirmCheckoutDialogState: ConfirmCheckoutDialogState = ConfirmCheckoutDialogState(),
    val cartProducts: PersistentList<CartProduct> = persistentListOf(),
    val bottomButtonText: String = ""
){
    val totalPrice: Double
        get() = cartProducts.sumOf { it.price * it.count }

    val totalPriceText: String
        get() = totalPrice.formatAsPriceTr()

    val showRemoveAllButton: Boolean
        get() = cartProducts.isNotEmpty()
}

data class ConfirmCheckoutDialogState(
    val title: String = "",
    val confirmText: String = "",
    val dismissText: String = ""
)

data class CartProduct(
    val id: String = "",
    val name: String = "",
    val attribute: String = "",
    val imageUrl: String = "",
    val price: Double = 0.00,
    val count: Int = 0
){
    val priceText: String
        get() = price.formatAsPriceTr()
}
