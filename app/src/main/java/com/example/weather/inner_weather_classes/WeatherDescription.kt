package com.example.weather.inner_weather_classes

import com.squareup.moshi.Json

data class WeatherDescription(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)



