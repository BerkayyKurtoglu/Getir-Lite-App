package com.berkay.feature.detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.berkay.common.Navigator
import com.berkay.feature.common.domain.CardCacheManager
import com.berkay.feature.common.domain.CartCacheModel
import com.berkay.feature.count.common.CartScreenRoute
import com.berkay.feature.detail.contract.ProductDetailRoute
import com.berkay.presentation.MVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val cacheManager: CardCacheManager,
    private val navigator: Navigator,
    savedStateHandle: SavedStateHandle,
) : MVIViewModel<ProductDetailUiState, ProductDetailAction, ProductDetailEffect>(savedStateHandle) {

    private val args = savedStateHandle.toRoute<ProductDetailRoute>()

    init {
        listenCart()
    }

    override fun initialState(savedStateHandle: SavedStateHandle): ProductDetailUiState {
        return ProductDetailUiState(
            title = "Ürün Detayı",
            id = args.id,
            name = args.name,
            price = args.price,
            cartPrice = args.cartPrice,
            attribute = args.attribute,
            imageUrl = args.imageUrl,
            count = args.count
        )
    }

    override fun handleEvent(event: ProductDetailAction) {
        when (event) {
            ProductDetailAction.AddProduct -> {
                addProduct()
            }

            ProductDetailAction.OnBackClick -> {
                viewModelScope.launch {
                    navigator.navigateUp()
                }
            }

            ProductDetailAction.RemoveProduct -> {
                removeProduct()
            }

            ProductDetailAction.OnCartClick -> {
                viewModelScope.launch {
                    navigator.navigate(CartScreenRoute)
                }
            }
        }
    }

    private fun listenCart() {
        viewModelScope.launch {
            cacheManager.cartCache.collectLatest { cachedProducts ->
                val product = uiState.value
                val totalPrice = cachedProducts.sumOf { it.price * it.count }
                val cachedProduct = cachedProducts.find { it.id == product.id }
                updateState {
                    copy(
                        count = cachedProduct?.count ?: 0,
                        cartPrice = totalPrice
                    )
                }
            }
        }
    }

    private fun addProduct() {
        val product = uiState.value
        cacheManager.increaseCountByIdOrAdd(
            CartCacheModel(
                id = product.id,
                name = product.name,
                price = product.price,
                image = product.imageUrl,
                count = product.count
            )
        )
    }

    private fun removeProduct() {
        val product = uiState.value
        cacheManager.decreaseCountById(product.id)
    }
}
