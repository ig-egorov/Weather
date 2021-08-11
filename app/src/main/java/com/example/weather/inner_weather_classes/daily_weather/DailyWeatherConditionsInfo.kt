package com.example.weather.inner_weather_classes.daily_weather

import com.example.weather.inner_weather_classes.Temperature
import com.example.weather.inner_weather_classes.WeatherDescription
import com.squareup.moshi.Json

data class DailyWeatherConditionsInfo(
    @Json(name = "dt")
    val date: Long,
    @Json(name = "temp")
    val temperature: Temperature,
    @Json(name = "weather")
    val weather: List<WeatherDescription>
)