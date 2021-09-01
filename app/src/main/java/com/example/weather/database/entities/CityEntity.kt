package com.example.weather.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weather.weather_models.CurrentCity

@Entity(tableName = "cities_table")
data class CityEntity(
    @PrimaryKey(autoGenerate = true)
    val cityId: Long = 0L,
    @ColumnInfo(name = "city_name")
    val cityName: String,
    val latitude: String,
    val longitude: String
)

fun CityEntity.asDomainModel(): CurrentCity {
    return CurrentCity(
        cityName = cityName,
        latitude = latitude,
        longitude = longitude
    )
}