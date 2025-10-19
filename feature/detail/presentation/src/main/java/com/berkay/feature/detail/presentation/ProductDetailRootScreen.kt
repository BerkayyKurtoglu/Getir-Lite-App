package com.berkay.feature.detail.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.berkay.feature.detail.presentation.screens.ProductDetailBottomBar
import com.berkay.feature.detail.presentation.screens.ProductDetailScreen
import com.berkay.feature.detail.presentation.screens.ProductDetailTopBar
import com.berkay.ui.components.getirscaffold.GetirScaffold

@Composable
fun ProductDetailRootScreen() {

    val viewModel: ProductDetailViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    GetirScaffold(
        topBar = {
            ProductDetailTopBar(
                titleText = uiState.title,
                cartPrice = uiState.cartPriceString,
                showCartTotalButton = uiState.showCartTotalButton,
                onBackClicked = {
                    viewModel.handleEvent(ProductDetailAction.OnBackClick)
                },
                onCartClicked = {
                    viewModel.handleEvent(ProductDetailAction.OnCartClick)
                }
            )
        },
        bottomBar = {
            ProductDetailBottomBar(
                productCount = uiState.count,
                onAddProductClick = {
                    viewModel.handleEvent(ProductDetailAction.AddProduct)
                },
                onRemoveProductClick = {
                    viewModel.handleEvent(ProductDetailAction.RemoveProduct)
                }
            )
        }
    ) {
        ProductDetailScreen(
            innerPaddingValues = it,
            uiState = uiState
        )
    }
}
