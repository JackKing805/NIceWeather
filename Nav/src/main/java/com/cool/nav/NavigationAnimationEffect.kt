package com.cool.nav

import android.app.Activity
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationAnimationEffect(
    startDestination: String,
    enterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition) =
        { fadeIn(animationSpec = tween(700)) },
    exitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition) =
        { fadeOut(animationSpec = tween(700)) },
    popEnterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition) = enterTransition,
    popExitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition) = exitTransition,
    builder: NavGraphBuilder.() -> Unit,
) {
    val navController = rememberAnimatedNavController()
    val activity = (LocalContext.current as? Activity)
    val flow = NavChannel.navChannel
    LaunchedEffect(activity, navController, flow) {
        flow.collect {
            if (activity?.isFinishing == true) {
                return@collect
            }
            navController.handleComposeNavigationIntent(it)
            navController.backQueue.forEachIndexed { index, navBackStackEntry ->
                Log.e(
                    "NavAnimationEffect",
                    "index:$index=NavigationEffects: ${navBackStackEntry.destination.route}",
                )
            }
        }
    }
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition=enterTransition,
        exitTransition =exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        builder = builder
    )
}