package com.example.weather.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weather.weather_models.HourlyWeather

@Entity(tableName = "hourly_weather_table")
data class HourlyWeatherEntity(
    @PrimaryKey(autoGenerate = false)
    val hourlyWeatherId: Int,
    val date: Long,
    val temperature: Double,
    val hourlyWeatherDescriptionId: Int,
    val hourlyWeatherDescriptionMain: String,
    val hourlyWeatherDescription: String,
    val hourlyWeatherDescriptionIcon: String
)

fun List<HourlyWeatherEntity>.asDomainModel(): List<HourlyWeather> {
    return map {
        HourlyWeather (
            hourlyWeatherId = it.hourlyWeatherId,
            date = it.date,
            temperature = it.temperature,
            hourlyWeatherDescription = it.hourlyWeatherDescription,
            hourlyWeatherDescriptionIcon = it.hourlyWeatherDescriptionIcon)
    }
}