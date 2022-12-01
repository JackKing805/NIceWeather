@file:OptIn(ExperimentalAnimationApi::class)

package com.cool.nicevideo.ui.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

private const val animDurationMillis = 400

fun NavGraphBuilder.horizontallyAnimatedComposable(
    route: String,
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit,
) {
    composable(
        route = route,
        content = content,
        enterTransition = {
            slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(
                animDurationMillis
            ))
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(animDurationMillis))
        },
        popEnterTransition = {
            slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(animDurationMillis))
        },
        popExitTransition = {
            slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(animDurationMillis))
        },
    )
}

fun NavGraphBuilder.verticallyAnimatedComposable(
    route: String,
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit,
) {
    composable(
        route = route,
        content = content,
        enterTransition = {
            slideIntoContainer(AnimatedContentScope.SlideDirection.Up, animationSpec = tween(animDurationMillis))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(animDurationMillis))
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(animDurationMillis))
        },
        popExitTransition = {
            slideOutOfContainer(AnimatedContentScope.SlideDirection.Down, animationSpec = tween(animDurationMillis))
        },
    )
}
