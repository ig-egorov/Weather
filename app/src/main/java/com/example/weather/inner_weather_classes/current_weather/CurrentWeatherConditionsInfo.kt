package com.example.weather.inner_weather_classes.current_weather

import com.example.weather.inner_weather_classes.WeatherDescription
import com.squareup.moshi.Json

data class CurrentWeatherConditionsInfo(
    @Json(name = "temp")
    val temperature: Double,
    @Json(name = "weather")
    val weather: List<WeatherDescription>
    )