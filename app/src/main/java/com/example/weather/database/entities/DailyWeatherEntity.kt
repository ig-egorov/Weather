package com.example.weather.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weather.inner_weather_classes.Temperature
@Entity(tableName = "daily_weather_table")
data class DailyWeatherEntity (
    @PrimaryKey(autoGenerate = false)
    val dailyWeatherId: Int,
    val date: Long,
    val maxTemperature: Double,
    val dailyWeatherDescriptionId: Int,
    val dailyWeatherDescriptionMain: String,
    val dailyWeatherDescription: String,
    val dailyWeatherDescriptionIcon: String
)