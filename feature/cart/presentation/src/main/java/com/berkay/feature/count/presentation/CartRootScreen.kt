package com.berkay.feature.count.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.berkay.feature.count.presentation.components.CartBottomBar
import com.berkay.feature.count.presentation.components.CartProductsTopBar
import com.berkay.feature.count.presentation.components.CartScreen
import com.berkay.feature.count.presentation.components.CheckoutConfirmDialog
import com.berkay.ui.components.contional.Conditional
import com.berkay.ui.components.getirscaffold.GetirScaffold

@Composable
fun CartRootScreen() {

    val viewModel: CartViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    GetirScaffold(
        topBar = {
            CartProductsTopBar(
                titleText = uiState.title,
                shouldShowRemoveAllButton = uiState.showRemoveAllButton,
                onBackClicked = {
                    viewModel.handleAction(CartAction.OnBackClicked)
                },
                onRemoveAllClicked = {
                    viewModel.handleAction(CartAction.OnRemoveAllClicked)
                }
            )
        },
        bottomBar = {
            CartBottomBar(
                priceText = uiState.totalPriceText,
                buttonText = uiState.bottomButtonText,
                onClick = {
                    viewModel.handleAction(CartAction.OnCheckoutButtonClicked)
                }
            )
        }
    ) {
        Conditional({uiState.showCheckoutDialog}){
            CheckoutConfirmDialog(
                title = uiState.confirmCheckoutDialogState.title,
                confirmText = uiState.confirmCheckoutDialogState.confirmText,
                dismissText = uiState.confirmCheckoutDialogState.dismissText,
                onConfirm = {
                    viewModel.handleAction(CartAction.OnConfirmCheckoutDialog)
                },
                onDismiss = {
                    viewModel.handleAction(CartAction.OnDismissCheckoutDialog)
                }
            )
        }
        CartScreen(
            innerPaddings = it,
            products = uiState.cartProducts,
            onAddClick = {
                viewModel.handleAction(CartAction.AddProduct(it))
            },
            onRemoveClick = {
                viewModel.handleAction(CartAction.RemoveProduct(it))
            }
        )
    }

}
