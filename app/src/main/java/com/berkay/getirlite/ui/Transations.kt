package com.berkay.getirlite.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

private const val TIME_DURATION = 350

val enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Start,
        animationSpec = tween(TIME_DURATION)
    )
}

val exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    ExitTransition.None
}

val popEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    EnterTransition.None
}

val popExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Down,
        animationSpec = tween(TIME_DURATION)
    )
}
