package com.example.weather.weather_models

import com.example.weather.inner_weather_classes.WeatherDescription
import com.example.weather.inner_weather_classes.current_weather.CurrentWeatherConditionsInfo

data class CurrentWeather(
    val currentWeatherDescription: List<WeatherDescription>,
    val currentWeatherConditionsInfo: CurrentWeatherConditionsInfo
)