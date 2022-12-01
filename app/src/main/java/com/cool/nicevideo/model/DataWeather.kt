package com.cool.nicevideo.model

data class DataWeather(
    val city: String,
    val cityid: String,
    val country: String,
    val day: Day,
    val errcode: Int,
    val errmsg: String,
    val hours: List<Hour>,
    val latitude: String,
    val longitude: String,
    val month: List<Month>,
    val timeZone: String,
    val updateTime: String,
    val updateTimeFormat: String,
    val updateTimeServer: String
)