package com.berkay.getirlite.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.berkay.feature.count.common.CartScreenRoute
import com.berkay.feature.count.presentation.CartRootScreen
import com.berkay.feature.detail.contract.ProductDetailRoute
import com.berkay.feature.detail.presentation.ProductDetailRootScreen
import com.berkay.feature.products.contract.ProductsScreenRoute
import com.berkay.feature.products.presentation.ProductsRootScreen

fun NavGraphBuilder.productsRootScreen() {
    composable<ProductsScreenRoute> {
        ProductsRootScreen()
    }
}

fun NavGraphBuilder.productDetailScreen() {
    composable<ProductDetailRoute> {
        ProductDetailRootScreen()
    }
}

fun NavGraphBuilder.cartRootScreen() {
    composable<CartScreenRoute> {
        CartRootScreen()
    }
}
