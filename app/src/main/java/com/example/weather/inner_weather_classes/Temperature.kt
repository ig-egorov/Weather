package com.example.weather.inner_weather_classes

import com.squareup.moshi.Json

data class Temperature(
    @Json(name = "day")
    val dayTemperature: Double,
    @Json(name = "min")
    val minTemperature: Double,
    @Json(name = "max")
    val maxTemperature: Double,
    @Json(name = "night")
    val nightTemperature: Double,
    @Json(name = "eve")
    val eveTemperature: Double,
    @Json(name = "morn")
    val mornTemperature: Double
)