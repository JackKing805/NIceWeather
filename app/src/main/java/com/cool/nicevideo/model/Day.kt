package com.cool.nicevideo.model

data class Day(
    val air: String,
    val air_level: String,
    val alarm: List<Alarm>,
    val altimeter: String,
    val aqi: Aqi?,
    val barometerTrend: String,
    val dewPoint: String,
    val feelsLike: String,
    val humidity: String,
    val icon: String,
    val phrase: String,
    val phrase_img: String,
    val temperature: String,
    val temperatureMaxSince7am: String,
    val uvDescription: String,
    val uvIndex: String,
    val visibility: String,
    val windDirCompass: String,
    val windDirDegrees: String,
    val windSpeed: String
)