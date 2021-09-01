package com.example.weather.inner_weather_classes.daily_weather

import com.example.weather.inner_weather_classes.Temperature
import com.example.weather.inner_weather_classes.WeatherDescription
import com.squareup.moshi.Json

data class DailyWeatherConditionsInfo(
    @Json(name = "dt")
    val date: Long,
    @Json(name = "sunrise")
    val sunriseTime: Long,
    @Json(name = "sunset")
    val sunsetTime: Long,
    @Json(name = "temp")
    val temperature: Temperature,
    @Json(name = "humidity")
    val humidity: Int,
    @Json(name = "wind_speed")
    val windSpeed: Double,
    @Json(name = "weather")
    val weather: List<WeatherDescription>
)