package com.cool.nicevideo.ui.pages.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HdrWeak
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cool.nicevideo.model.Hour

@Composable
fun HomePage(homeViewModel: HomeViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xff6efae6),
                        Color(0xff72efee)
                    )
                )
            )
            .systemBarsPadding()
    ) {
        HomeTopToolBar(homeViewModel)
        WeatherInfo(homeViewModel)
        Spacer(modifier = Modifier.height(50.dp))
        ThreeDays(homeViewModel)
        Spacer(modifier = Modifier.height(40.dp))
        HourList(homeViewModel)
    }
}


