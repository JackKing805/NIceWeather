package com.cool.nicevideo.ui.pages.home

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.FmdGood
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cool.nicevideo.extensions.ofMap
import com.cool.nicevideo.extensions.print

@Composable
fun HomeTopToolBar(homeViewModel: HomeViewModel) {
    val context = LocalContext.current
    val uiState by homeViewModel.uiState.collectAsState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .padding(top = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            homeViewModel.requireLocationPermission(context)
        }) {
            Icon(
                imageVector = Icons.Filled.FmdGood,
                contentDescription = null,
                tint = Color(0xff2a3263),
                modifier = Modifier.size(20.dp)
            )
        }
        when (val location = uiState.location) {
            HomeLocation.GrantPermission -> {
                Text(
                    text = "Grant Permission",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xff2a3263)
                )
            }
            is HomeLocation.Location -> {
                Text(
                    text = location.city,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xff2a3263)
                )
            }
            HomeLocation.Loading -> {
                val loadingAnimation = remember {
                    Animatable(0f)
                }

                LaunchedEffect(key1 = Unit, block = {
                    loadingAnimation.animateTo(
                        1f,
                        repeatable(Int.MAX_VALUE, animation = tween(1500, easing = LinearEasing))
                    )
                })

                val dot = if (loadingAnimation.value < 0.3f) {
                    "."
                } else if (loadingAnimation.value < 0.6f) {
                    ".."
                } else {
                    "..."
                }

                Text(
                    text = "loading$dot",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xff2a3263)
                )
            }
        }
        Spacer(modifier = Modifier.width(5.dp))
        Icon(
            imageVector = Icons.Filled.ExpandMore,
            contentDescription = null,
            tint = Color(0xff2a3263),
            modifier = Modifier.size(15.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { }) {
            Icon(
                imageVector = Icons.Filled.CalendarMonth,
                contentDescription = null,
                tint = Color(0xff2a3263),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}