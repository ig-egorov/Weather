package com.example.weather.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weather.inner_weather_classes.WeatherDescription
import com.example.weather.inner_weather_classes.current_weather.CurrentWeatherConditionsInfo

@Entity(tableName = "current_weather_table")
data class CurrentWeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val currentWeatherId: Long = 0L,
    val temperature: Double,
    val currentWeatherDescriptionId: Int,
    val currentWeatherDescriptionMain: String,
    val currentWeatherDescription: String,
    val currentWeatherDescriptionIcon: String
)

