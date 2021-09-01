package com.example.weather.weather_models

data class CurrentWeather(
    val temperature: Double,
    val currentWeatherDescription: String,
    val currentWeatherDescriptionIcon: String
)