package com.example.weather.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weather.weather_models.DailyWeather

@Entity(tableName = "daily_weather_table")
data class DailyWeatherEntity (
    @PrimaryKey(autoGenerate = false)
    val dailyWeatherId: Int,
    val date: Long,
    val sunriseTime: Long,
    val sunsetTime: Long,
    val maxTemperature: Double,
    val minTemperature: Double,
    val humidity: Int,
    val windSpeed: Double,
    val dailyWeatherDescriptionId: Int,
    val dailyWeatherDescriptionMain: String,
    val dailyWeatherDescription: String,
    val dailyWeatherDescriptionIcon: String
)

fun List<DailyWeatherEntity>.asDomainModel(): List<DailyWeather> {
    return map {
        DailyWeather(
            dailyWeatherId = it.dailyWeatherId,
            date = it.date,
            sunriseTime = it.sunriseTime,
            sunsetTime = it.sunsetTime,
            maxTemperature = it.maxTemperature,
            minTemperature = it.minTemperature,
            humidity = it.humidity,
            windSpeed = it.windSpeed,
            dailyWeatherDescription = it.dailyWeatherDescription,
            dailyWeatherDescriptionIcon = it.dailyWeatherDescriptionIcon
        )
    }
}

fun DailyWeatherEntity.asDomainModel(): DailyWeather {
    return DailyWeather(
        dailyWeatherId = dailyWeatherId,
        date = date,
        sunriseTime = sunriseTime,
        sunsetTime = sunsetTime,
        maxTemperature = maxTemperature,
        minTemperature = minTemperature,
        humidity = humidity,
        windSpeed = windSpeed,
        dailyWeatherDescription = dailyWeatherDescription,
        dailyWeatherDescriptionIcon = dailyWeatherDescriptionIcon
    )
}