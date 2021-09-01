package com.example.weather.weather_models

data class DailyWeather(
    val dailyWeatherId: Int,
    val date: Long,
    val sunriseTime: Long,
    val sunsetTime: Long,
    val maxTemperature: Double,
    val minTemperature: Double,
    val humidity: Int,
    val windSpeed: Double,
    val dailyWeatherDescription: String,
    val dailyWeatherDescriptionIcon: String
)
