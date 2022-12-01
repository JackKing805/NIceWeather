package com.cool.nicevideo.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.cool.nav.NavigationAnimationEffect
import com.cool.nicevideo.ui.pages.home.HomePage
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(){
    NavigationAnimationEffect(startDestination = HomeDestination.route, builder = {
       composable(HomeDestination.route){
           HomePage()
       }


    })
}