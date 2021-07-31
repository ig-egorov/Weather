package com.example.weather.network

import com.example.weather.inner_weather_classes.WeatherDescription
import com.example.weather.inner_weather_classes.current_weather.CurrentWeatherConditionsInfo
import com.example.weather.weather_models.CurrentWeather
import com.squareup.moshi.Json

data class CurrentWeatherDTO(
    @Json(name = "weather")
    val currentWeatherDescription: List<WeatherDescription>,
    @Json(name = "main")
    val currentWeatherConditionsInfo: CurrentWeatherConditionsInfo,
)

fun CurrentWeatherDTO.asDomainModel(): CurrentWeather {
    return CurrentWeather(
        currentWeatherDescription = currentWeatherDescription,
        currentWeatherConditionsInfo = currentWeatherConditionsInfo,
    )
}

