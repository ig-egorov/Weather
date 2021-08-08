package com.example.weather.network

import com.example.weather.database.entities.CurrentWeatherEntity
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
fun CurrentWeatherDTO.asDatabaseModel(): CurrentWeatherEntity {
    return CurrentWeatherEntity(
        temperature = currentWeatherConditionsInfo.temperature,
        currentWeatherDescriptionId = currentWeatherDescription[0].id,
        currentWeatherDescriptionMain = currentWeatherDescription[0].main,
        currentWeatherDescription = currentWeatherDescription[0].description,
        currentWeatherDescriptionIcon = currentWeatherDescription[0].icon
    )
}

