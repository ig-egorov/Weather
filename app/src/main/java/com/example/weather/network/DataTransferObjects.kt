package com.example.weather.network

import com.example.weather.database.entities.CurrentWeatherEntity
import com.example.weather.database.entities.DailyWeatherEntity
import com.example.weather.database.entities.HourlyWeatherEntity
import com.example.weather.inner_weather_classes.current_weather.CurrentWeatherConditionsInfo
import com.example.weather.inner_weather_classes.daily_weather.DailyWeatherConditionsInfo
import com.example.weather.inner_weather_classes.hourly_weather.HourlyWeatherConditionsInfo
import com.squareup.moshi.Json

data class OverallWeatherDTO(
    @Json(name = "timezone_offset")
    val timeZoneOffset: Long,
    @Json(name = "current")
    val currentWeather: CurrentWeatherConditionsInfo,
    @Json(name = "hourly")
    val hourlyWeather: List<HourlyWeatherConditionsInfo>,
    @Json(name = "daily")
    val dailyWeather: List<DailyWeatherConditionsInfo>
)


fun OverallWeatherDTO.asCurrentWeatherDatabaseModel(): CurrentWeatherEntity {
    return CurrentWeatherEntity(
        temperature = currentWeather.temperature,
        currentWeatherDescriptionId = currentWeather.weather[0].id,
        currentWeatherDescriptionMain = currentWeather.weather[0].main,
        currentWeatherDescription = currentWeather.weather[0].description,
        currentWeatherDescriptionIcon = currentWeather.weather[0].icon
    )
}

fun OverallWeatherDTO.asHourlyWeatherDatabaseModel(): Array<HourlyWeatherEntity> {
    return hourlyWeather.map {
        HourlyWeatherEntity(
            hourlyWeatherId = hourlyWeather.indexOf(it),
            date = it.date,
            temperature = it.temperature,
            hourlyWeatherDescriptionId = it.weather[0].id,
            hourlyWeatherDescriptionMain = it.weather[0].main,
            hourlyWeatherDescription = it.weather[0].description,
            hourlyWeatherDescriptionIcon = it.weather[0].icon
        )
    }.toTypedArray()
}

fun OverallWeatherDTO.asDailyWeatherDatabaseModel(): Array<DailyWeatherEntity> {
    return dailyWeather.map {
        DailyWeatherEntity(
            dailyWeatherId = dailyWeather.indexOf(it),
            date = it.date,
            sunriseTime = it.sunriseTime,
            sunsetTime = it.sunsetTime,
            maxTemperature = it.temperature.maxTemperature,
            minTemperature = it.temperature.minTemperature,
            humidity = it.humidity,
            windSpeed = it.windSpeed,
            dailyWeatherDescriptionId = it.weather[0].id,
            dailyWeatherDescriptionMain = it.weather[0].main,
            dailyWeatherDescription = it.weather[0].description,
            dailyWeatherDescriptionIcon = it.weather[0].icon
        )
    }.toTypedArray()
}
