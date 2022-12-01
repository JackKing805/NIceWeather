package com.cool.nicevideo.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun StatusBar(color: Color = Color.Transparent) {
    Spacer(
        modifier = Modifier
            .background(color)
            .statusBarsPadding()
            .fillMaxWidth()
    )
}

@Composable
fun NavigationBar(color: Color = Color.Transparent) {
    Spacer(
        modifier = Modifier
            .background(color)
            .navigationBarsPadding()
            .fillMaxWidth()
    )
}