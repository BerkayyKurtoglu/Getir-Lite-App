package com.berkay.feature.products.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkay.common.Navigator
import com.berkay.domain.onError
import com.berkay.domain.onSuccess
import com.berkay.feature.common.domain.CardCacheManager
import com.berkay.feature.count.common.CartScreenRoute
import com.berkay.feature.detail.contract.ProductDetailRoute
import com.berkay.feature.products.domain.ProductsRepository
import com.berkay.ui.components.productcard.ProductUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
internal class ProductsViewModel @Inject constructor(
    private val cacheManager: CardCacheManager,
    private val repository: ProductsRepository,
    private val mapper: ProductsMapper,
    private val navigator: Navigator,
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        ProductsReadyState(
            screenState = ProductsUiState(
                title = "Ürünler"
            ),
            errorDialogState = ErrorDialogState(
                title = "Hata",
                confirmText = "Tamam"
            )
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        getProducts()
        listenForCartChanges()
    }

    fun handleAction(action: ProductsAction) {
        when (action) {
            ProductsAction.OnCartClick -> {
                viewModelScope.launch {
                    navigator.navigate(CartScreenRoute)
                }
            }

            ProductsAction.OnConfirmErrorDialog -> {
                confirmErrorDialog()
            }

            is ProductsAction.OnAddVerticalProduct -> {
                addVerticalProductToCount(action.product)
            }

            is ProductsAction.OnRemoveVerticalProduct -> {
                removeVerticalProductFromCount(action.product)
            }

            is ProductsAction.OnAddHorizontalProduct -> {
                addHorizontalProductToCount(action.product)
            }

            is ProductsAction.OnRemoveHorizontalProduct -> {
                removeHorizontalProductFromCount(action.product)
            }

            is ProductsAction.OnProductClick -> {
                viewModelScope.launch {
                    navigator.navigate(
                        ProductDetailRoute(
                            id = action.product.id,
                            name = action.product.name,
                            price = action.product.price,
                            cartPrice = _uiState.value.screenState.cartPrice,
                            attribute = action.product.attribute,
                            imageUrl = action.product.imageUrl,
                            count = action.product.count
                        )
                    )
                }
            }
        }
    }

    private fun listenForCartChanges() {
        viewModelScope.launch {
            cacheManager.cartCache.collectLatest { cartList ->
                val verticalDeferred = async {
                    _uiState.value.screenState.verticalProductUiModels.map { product ->
                        val matched = cartList.find { it.id == product.id }
                        if (matched != null) product.copy(count = matched.count) else product.copy(
                            count = 0
                        )
                    }
                }

                val horizontalDeferred = async {
                    _uiState.value.screenState.horizontalProductUiModels.map { product ->
                        val matched = cartList.find { it.id == product.id }
                        if (matched != null) product.copy(count = matched.count) else product.copy(
                            count = 0
                        )
                    }
                }

                val vertical = verticalDeferred.await()
                val horizontal = horizontalDeferred.await()

                withContext(Dispatchers.Default) {
                    val updatedCartPrice = (vertical + horizontal)
                        .sumOf { it.price * it.count }
                        .coerceAtLeast(0.0)

                    _uiState.update {
                        it.copy(
                            screenState = it.screenState.copy(
                                verticalProductUiModels = vertical.toPersistentList(),
                                horizontalProductUiModels = horizontal.toPersistentList(),
                                cartPrice = updatedCartPrice
                            )
                        )
                    }
                }
            }
        }
    }

    private fun getProducts() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val verticalProductsDeferred = async { repository.getProducts() }
            val horizontalProductsDeferred = async { repository.getSuggestedProducts() }

            val verticalProducts = verticalProductsDeferred.await()
            val horizontalProducts = horizontalProductsDeferred.await()

            verticalProducts.onSuccess { products ->
                val productsUiModel =
                    mapper.mapProductsToProductsUiModel(products).toPersistentList()
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorState = null,
                        screenState = it.screenState.copy(
                            verticalProductUiModels = productsUiModel
                        )
                    )
                }
            }.onError {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorState = ErrorState(
                            message = it.errorState?.message ?: "Bir hata oluştu"
                        )
                    )
                }
            }
            horizontalProducts.onSuccess { suggestedProducts ->
                val productsUiModel =
                    mapper.mapSuggestedProductsToProductsUiModel(suggestedProducts)
                        .toPersistentList()
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorState = null,
                        screenState = it.screenState.copy(
                            horizontalProductUiModels = productsUiModel
                        )
                    )
                }
            }.onError { message ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorState = ErrorState(
                            message = message
                        )
                    )
                }
            }
        }
    }

    private fun addVerticalProductToCount(product: ProductUiModel) {
        cacheManager.increaseCountByIdOrAdd(mapper.mapToCartCacheModel(product))
    }

    private fun addHorizontalProductToCount(product: ProductUiModel) {
        cacheManager.increaseCountByIdOrAdd(mapper.mapToCartCacheModel(product))
    }

    private fun removeVerticalProductFromCount(product: ProductUiModel) {
        cacheManager.decreaseCountById(product.id)
    }

    private fun removeHorizontalProductFromCount(product: ProductUiModel) {
        cacheManager.decreaseCountById(product.id)
    }

    private fun confirmErrorDialog(){
        _uiState.update {
            it.copy(
                errorState = null
            )
        }
        getProducts()
    }

}
