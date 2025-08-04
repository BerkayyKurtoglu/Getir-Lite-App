package com.berkay.feature.detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.berkay.common.Navigator
import com.berkay.feature.common.domain.CardCacheManager
import com.berkay.feature.common.domain.CartCacheModel
import com.berkay.feature.detail.contract.ProductDetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

        val updatedCartPrice = _uiState.value.cartPrice + product.price
        _uiState.value = product.copy(
            count = product.count + 1,
            cartPrice = updatedCartPrice,
        )
    }

    private fun removeProduct(){
        val product = _uiState.value
        cacheManager.decreaseCountById(product.id)

        val updatedCartPrice = _uiState.value.cartPrice - product.price
        _uiState.value = product.copy(
            count = product.count - 1,
            cartPrice = updatedCartPrice
        )
    }

}
