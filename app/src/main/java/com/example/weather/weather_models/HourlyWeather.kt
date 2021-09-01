package com.example.weather.weather_models

data class HourlyWeather(
    val hourlyWeatherId: Int,
    val date: Long,
    val temperature: Double,
    val hourlyWeatherDescription: String,
    val hourlyWeatherDescriptionIcon: String
)