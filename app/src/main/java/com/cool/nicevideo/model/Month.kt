package com.cool.nicevideo.model

data class Month(
    val date: String,
    val dateOfWeek: String,
    val day: DayX,
    val moonIcon: String,
    val moonPhrase: String,
    val moonrise: String,
    val moonset: String,
    val night: Night,
    val sunrise: String,
    val sunset: String
)