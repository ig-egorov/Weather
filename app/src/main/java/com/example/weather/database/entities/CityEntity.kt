package com.example.weather.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities_table")
data class CityEntity(
    @PrimaryKey(autoGenerate = true)
    val cityId: Int = 0,
    @ColumnInfo(name = "city_name")
    val cityName: String
)