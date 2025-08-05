package com.berkay.feature.detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.berkay.common.Navigator
import com.berkay.feature.common.domain.CardCacheManager
import com.berkay.feature.common.domain.CartCacheModel
import com.berkay.feature.count.common.CartScreenRoute
import com.berkay.feature.detail.contract.ProductDetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val cacheManager: CardCacheManager,
    private val navigator: Navigator,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val args = savedStateHandle.toRoute<ProductDetailRoute>()

    private val _uiState = MutableStateFlow(ProductDetailUiState(
        title = "Ürün Detayı",
        id = args.id,
        name = args.name,
        price = args.price,
        cartPrice = args.cartPrice,
        attribute = args.attribute,
        imageUrl = args.imageUrl,
        count = args.count
    ))
    val uiState = _uiState.asStateFlow()

    init {
        listenCart()
    }

    fun handleAction(action: ProductDetailAction){
        when(action){
            ProductDetailAction.AddProduct -> {
                addProduct()
            }
            ProductDetailAction.OnBackClick -> {
                viewModelScope.launch { navigator.navigateUp() }
            }
            ProductDetailAction.RemoveProduct -> {
                removeProduct()
            }

            ProductDetailAction.OnCartClick -> {
                viewModelScope.launch { navigator.navigate(CartScreenRoute) }
            }
        }
    }

    private fun listenCart(){
        viewModelScope.launch {
            cacheManager.cartCache.collectLatest { cachedProducts ->
                val product = _uiState.value
                val totalPrice = cachedProducts.sumOf { it.price * it.count }
                val cachedProduct = cachedProducts.find { it.id == product.id }
                _uiState.update {
                    it.copy(
                        count = cachedProduct?.count ?: 0,
                        cartPrice = totalPrice
                    )
                }
            }
        }
    }

    private fun addProduct(){
        val product = _uiState.value
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

    private fun removeProduct(){
        val product = _uiState.value
        cacheManager.decreaseCountById(product.id)
    }

}
