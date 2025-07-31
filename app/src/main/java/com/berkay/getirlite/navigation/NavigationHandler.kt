package com.berkay.getirlite.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.berkay.common.NavigationCommand
import com.berkay.common.Navigator

@Composable
internal fun NavigationHandler(
    navigator: Navigator,
    navController: NavHostController,
) {

    LaunchedEffect(navController) {
        navigator.screenFlow.collect {
            when (it) {
                is NavigationCommand.Destination -> {
                    navController.navigate(it)
                }

                is NavigationCommand.Command -> {
                    when (it) {
                        ComposeNavigatorCommand.NavigateUp -> {
                            navController.navigateUp()
                        }

                        is ComposeNavigatorCommand.NavigateUpTo -> {
                            navController.popBackStack(
                                route = it.to,
                                inclusive = it.inclusive,
                                saveState = it.saveState
                            )
                        }

                        is ComposeNavigatorCommand.NavigateWithClearBackStackTo -> {
                            navController.navigate(it.to) {
                                popUpTo(it.clearTo) {
                                    inclusive = it.inclusive
                                }
                            }
                        }

                        is ComposeNavigatorCommand.NavigateWithClearBackStack -> {
                            navController.navigate(it.to) {
                                popUpTo(0) {
                                    inclusive = it.inclusive
                                }
                            }
                        }

                        else -> Unit
                    }
                }
            }
        }
    }

}