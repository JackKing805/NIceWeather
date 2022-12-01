package com.cool.nicevideo.ui.pages.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HdrWeak
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cool.nicevideo.model.Hour

@Composable
fun HourList(homeViewModel: HomeViewModel) {
    val uiData by homeViewModel.uiState.collectAsState()
    val location = uiData.location
    if (location is HomeLocation.Location) {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp), content = {
            item { Spacer(modifier = Modifier.width(0.dp)) }
            items(location.dataWeather.hours.size, key = {
                it
            }) {
                HourCard(location.dataWeather.hours[it])
            }
            item { Spacer(modifier = Modifier.width(0.dp)) }
        })
    }
}

@Composable
private fun HourCard(hour: Hour) {
    Surface(
        modifier = Modifier
            .width(120.dp)
            .height(180.dp),
        color = Color.White.copy(alpha = 0.3f),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement =Arrangement.Center) {
            Text(
                text = "${hour.time},${if (hour.dayOrNight=="N") "晚上" else "早上"}",
                fontSize = 13.sp,
                fontWeight = FontWeight.W500,
                color = Color(0xff2e868f),
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(20.dp))
            Image(painter = painterResource(id = static[hour.wea_img]!!), contentDescription = null, modifier = Modifier.size(40.dp), contentScale = ContentScale.Crop)
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = hour.temfeels.toString(),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xff242f59)
            )
        }
    }
}