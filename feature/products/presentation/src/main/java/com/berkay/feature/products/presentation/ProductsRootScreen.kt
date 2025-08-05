package com.berkay.feature.products.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.berkay.feature.products.presentation.screens.ErrorDialog
import com.berkay.feature.products.presentation.screens.ProductsScreen
import com.berkay.feature.products.presentation.screens.ProductsTopBar
import com.berkay.ui.components.contional.Conditional
import com.berkay.ui.components.getirscaffold.GetirScaffold
import com.berkay.ui.theme.LocalColorScheme

@Composable
fun ProductsRootScreen() {

    val localColorScheme = LocalColorScheme.current
    val viewModel: ProductsViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    GetirScaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .drawBehind {
                        drawRect(
                            color = localColorScheme.corePrimaryColor
                        )
                    },
                contentAlignment = Alignment.BottomCenter
            ) {
                ProductsTopBar(
                    cartPrice = uiState.screenState.cartPriceString,
                    titleText = uiState.screenState.title,
                    showCartTotalButton = uiState.screenState.showCartTotalButton,
                    onCartClick = {
                        viewModel.handleAction(ProductsAction.OnCartClick)
                    }
                )
            }
        }
    ) {
        Conditional({uiState.isLoading}){
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }

        Conditional({uiState.showErrorDialog}) {
            ErrorDialog(
                title = uiState.errorDialogState.title,
                message = uiState.errorState?.message ?: "",
                confirmText = uiState.errorDialogState.confirmText,
                onConfirm = {
                    viewModel.handleAction(ProductsAction.OnConfirmErrorDialog)
                }
            )
        }

        ProductsScreen(
            isLoading = uiState.isLoading,
            verticalProducts = uiState.screenState.verticalProductUiModels,
            horizontalProducts = uiState.screenState.horizontalProductUiModels,
            onAddVerticalProductClick = {
                viewModel.handleAction(ProductsAction.OnAddVerticalProduct(it))
            },
            onRemoveVerticalProductClick = {
                viewModel.handleAction(ProductsAction.OnRemoveVerticalProduct(it))
            },
            onAddHorizontalProductClick = {
                viewModel.handleAction(ProductsAction.OnAddHorizontalProduct(it))
            },
            onRemoveHorizontalProductClick = {
                viewModel.handleAction(ProductsAction.OnRemoveHorizontalProduct(it))
            },
            onProductClick = {
                viewModel.handleAction(ProductsAction.OnProductClick(it))
            },
            contentInnerPadding = it
        )
    }
}
