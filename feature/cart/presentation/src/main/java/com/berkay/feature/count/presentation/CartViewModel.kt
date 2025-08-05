package com.berkay.feature.count.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkay.common.Navigator
import com.berkay.feature.common.domain.CardCacheManager
import com.berkay.feature.detail.contract.ProductDetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cacheManager: CardCacheManager,
    private val mapper: CartMapper,
    private val navigator: Navigator
) : ViewModel() {

    private val _uiState = MutableStateFlow(CartUiState(
        bottomButtonText = "Siparişi Tamamla",
        confirmCheckoutDialogState = ConfirmCheckoutDialogState(
            title = "Siparişiniz alınacak onaylıyor musunuz ?",
            pricePrefixText = "Toplam Tutar : ",
            confirmText = "Evet",
            dismissText = "Hayır"
        )
    ))
    val uiState = _uiState.asStateFlow()

    init {
        getCartProducts()
    }

    fun handleAction(action: CartAction) {
        when (action) {
            is CartAction.AddProduct -> {
                addProductToCart(action.product)
            }

            is CartAction.RemoveProduct -> {
                removeProductFromCart(action.product)
            }

            is CartAction.OnProductClick -> {
                navigateToProductDetail(action.product)
            }

            CartAction.OnBackClicked -> {
                viewModelScope.launch {
                    navigator.navigateUp()
                }
            }

            CartAction.OnRemoveAllClicked -> {
                removeAllProductsFromCart()
            }

            CartAction.OnCheckoutButtonClicked -> {
                checkOut()
            }

            CartAction.OnConfirmCheckoutDialog -> {
                confirmCheckoutDialog()
            }
            CartAction.OnDismissCheckoutDialog -> {
                dismissCheckoutDialog()
            }
        }
    }

    private fun getCartProducts() {
        viewModelScope.launch {
            cacheManager.cartCache.collectLatest { cacheModels ->
                if (cacheModels.isEmpty()){
                    viewModelScope.launch { navigator.navigateUp() }
                } else{
                    val result = mapper.mapToCartProducts(cacheModels)
                    _uiState.update {
                        it.copy(
                            cartProducts = result.toPersistentList()
                        )
                    }
                }
            }
        }
    }

    private fun addProductToCart(product: CartProduct) {
        cacheManager.increaseCountByIdOrAdd(mapper.mapToCacheModel(product))
    }

    private fun removeProductFromCart(product: CartProduct) {
        cacheManager.decreaseCountById(product.id)
    }

    private fun navigateToProductDetail(product: CartProduct){
        viewModelScope.launch {
            navigator.navigate(ProductDetailRoute(
                id = product.id,
                name = product.name,
                imageUrl = product.imageUrl,
                price = product.price,
                attribute = product.attribute,
                cartPrice = _uiState.value.totalPrice,
                count = product.count
            ))
        }
    }

    private fun removeAllProductsFromCart() {
        cacheManager.emptyCart()
    }

    private fun checkOut() {
        _uiState.update {
            it.copy(
                showCheckoutDialog = true
            )
        }
    }

    private fun dismissCheckoutDialog() {
        _uiState.update {
            it.copy(
                showCheckoutDialog = false
            )
        }
    }

    private fun confirmCheckoutDialog() {
        dismissCheckoutDialog()
        cacheManager.emptyCart()
    }

}
