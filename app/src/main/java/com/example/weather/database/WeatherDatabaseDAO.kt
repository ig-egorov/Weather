package com.example.weather.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.weather.database.entities.CityEntity
import com.example.weather.database.entities.CurrentWeatherEntity

@Dao
interface WeatherDatabaseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentCity(cityEntity: CityEntity)

    @Query("SELECT * FROM cities_table ORDER BY cityId DESC LIMIT 1")
    fun getCurrentCity(): LiveData<CityEntity>

    @Query("SELECT city_name FROM cities_table ORDER BY cityId DESC LIMIT 1")
    suspend fun getCityData(): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeather(currentWeatherEntity: CurrentWeatherEntity)

    @Query("SELECT * FROM current_weather_table ORDER BY currentWeatherId DESC LIMIT 1")
    fun getCurrentWeather(): LiveData<CurrentWeatherEntity>
}