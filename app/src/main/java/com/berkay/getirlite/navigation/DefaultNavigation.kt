package com.berkay.getirlite.navigation

import com.berkay.common.NavigationCommand
import com.berkay.common.Navigator
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

sealed interface ComposeNavigatorCommand : NavigationCommand.Command {

    data object NavigateUp : NavigationCommand.Command

    data class NavigateWithClearBackStackTo(
        val to: NavigationCommand.Destination,
        val clearTo: NavigationCommand.Destination,
        val inclusive: Boolean
    ) : ComposeNavigatorCommand

    data class NavigateWithClearBackStack(
        val to: NavigationCommand.Destination,
        val inclusive: Boolean
    ) : ComposeNavigatorCommand

    data class NavigateUpTo(
        val to: NavigationCommand,
        val inclusive: Boolean,
        val saveState: Boolean
    ) : ComposeNavigatorCommand

}

class DefaultNavigation @Inject constructor() : Navigator {

    private val _screenChannel = Channel<NavigationCommand>()
    override val screenFlow: Flow<NavigationCommand> = _screenChannel.receiveAsFlow()

    override suspend fun navigate(screen: NavigationCommand.Destination) {
        _screenChannel.send(screen)
    }

    override suspend fun navigateWithClearBackStackTo(
        to: NavigationCommand.Destination,
        clearTo: NavigationCommand.Destination,
        inclusive: Boolean,
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun navigateWithClearBackStack(
        to: NavigationCommand.Destination,
        inclusive: Boolean,
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun navigateUp() {
        _screenChannel.send(ComposeNavigatorCommand.NavigateUp)
    }

    override suspend fun <S : NavigationCommand.Destination> navigateUpTo(
        paparaScreen: S,
        inclusive: Boolean,
        saveState: Boolean,
    ) {
        TODO("Not yet implemented")
    }


}