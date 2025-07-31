package com.berkay.common

import kotlinx.coroutines.flow.Flow

interface Navigator {

    val screenFlow: Flow<NavigationCommand>

    suspend fun navigate(screen: NavigationCommand.Destination)

    suspend fun navigateWithClearBackStackTo(
        to: NavigationCommand.Destination,
        clearTo: NavigationCommand.Destination,
        inclusive: Boolean = true
    )

    suspend fun navigateWithClearBackStack(
        to: NavigationCommand.Destination,
        inclusive: Boolean = true
    )

    suspend fun navigateUp()

    suspend fun <S : NavigationCommand.Destination> navigateUpTo(
        paparaScreen: S,
        inclusive: Boolean = false,
        saveState: Boolean = false
    )

}