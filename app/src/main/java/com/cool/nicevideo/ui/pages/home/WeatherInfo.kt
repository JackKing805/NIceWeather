package com.cool.nicevideo.ui.pages.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Water
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import java.util.*

private fun isNight():Boolean{
    val calendar = Calendar.getInstance()
    val get = calendar.get(Calendar.HOUR_OF_DAY)
    return get>=20 || get<=7
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WeatherInfo(homeViewModel: HomeViewModel){
    val uiData by homeViewModel.uiState.collectAsState()

    AnimatedContent(targetState = uiData.location) {
        when (it) {
            HomeLocation.GrantPermission -> {}
            HomeLocation.Loading -> {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    CircularProgressIndicator(modifier = Modifier.size(30.dp))
                }
            }
            is HomeLocation.Location -> {
                val today = it.dataWeather.month[0]
                val isNight = isNight()
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    AnimationWeather(url = if (isNight) dynamic[today.night.phrase_img]!! else dynamic[today.day.phrase_img]!!)
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = if (isNight) today.night.phrase else today.day.phrase,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.W500,
                        color = Color(0xff2e868f)
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = if (isNight) today.night.temperature else today.day.temperature,
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xff242f59)
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.Speed,
                            contentDescription = null,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "${if (isNight) today.night.windSpeed else today.day.windSpeed} km/h",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W500,
                            color = Color(0xff2e868f)
                        )
                        Spacer(modifier = Modifier.width(30.dp))
                        Icon(
                            imageVector = Icons.Filled.Water,
                            contentDescription = null,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "${if (isNight) today.night.humidity else today.day.humidity} %",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W500,
                            color = Color(0xff2e868f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AnimationWeather(url:String){
    val composition by rememberLottieComposition(LottieCompositionSpec.Url(url))
    val progress by animateLottieCompositionAsState(composition, iterations = Int.MAX_VALUE, cancellationBehavior = LottieCancellationBehavior.Immediately)
    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = Modifier.size(200.dp),
        contentScale = ContentScale.Crop
    )
}