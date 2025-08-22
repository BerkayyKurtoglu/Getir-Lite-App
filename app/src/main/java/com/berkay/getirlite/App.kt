package com.berkay.getirlite

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.berkay.common.Navigator
import com.berkay.feature.count.common.CartScreenRoute
import com.berkay.feature.count.presentation.CartRootScreen
import com.berkay.feature.detail.contract.ProductDetailRoute
import com.berkay.feature.detail.presentation.ProductDetailRootScreen
import com.berkay.feature.products.contract.ProductsScreenRoute
import com.berkay.feature.products.presentation.ProductsRootScreen
import com.berkay.getirlite.navigation.NavigationHandler
import com.berkay.getirlite.navigation.cartRootScreen
import com.berkay.getirlite.navigation.productDetailScreen
import com.berkay.getirlite.navigation.productsRootScreen
import com.berkay.getirlite.ui.enterTransition
import com.berkay.getirlite.ui.exitTransition
import com.berkay.getirlite.ui.popEnterTransition
import com.berkay.getirlite.ui.popExitTransition

@Composable
fun App(
    navigator: Navigator,
) {
    val navController = rememberNavController()

    NavigationHandler(
        navigator = navigator,
        navController = navController
    )

    NavHost(
        navController = navController,
        startDestination = ProductsScreenRoute,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition
    ) {
        productsRootScreen()
        productDetailScreen()
        cartRootScreen()
    }

}
