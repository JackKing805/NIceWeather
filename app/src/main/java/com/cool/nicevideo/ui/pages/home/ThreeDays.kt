package com.cool.nicevideo.ui.pages.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun ThreeDays(homeViewModel: HomeViewModel){
    val uiData by homeViewModel.uiState.collectAsState()
    val location = uiData.location
    if (location is HomeLocation.Location){
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            location.dataWeather.month.subList(0, 3).forEach {
                Text(
                    text = "${it.dateOfWeek},${it.date.substring(5)}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.W500,
                    color = Color(0xff2f2e62),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}