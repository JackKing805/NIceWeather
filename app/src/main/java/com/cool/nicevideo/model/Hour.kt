package com.cool.nicevideo.model

data class Hour(
    val cloudCover: Int,
    val dayOfWeek: String,
    val dayOrNight: String,
    val humidity: Int,
    val icon: Int,
    val precipPct: Int,
    val pressure: Double,
    val tem: Int,
    val temfeels: Int,
    val time: String,
    val timeUtc: Int,
    val uvIndex: Int,
    val visibility: Int,
    val wea: String,
    val wea_img: String,
    val wind: String,
    val windSpeed: Int
)