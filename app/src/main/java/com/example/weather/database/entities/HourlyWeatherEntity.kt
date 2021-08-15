package com.example.weather.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

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