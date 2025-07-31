package com.berkay.common

sealed interface NavigationCommand {
    interface Destination : NavigationCommand
    interface Command : NavigationCommand
}
