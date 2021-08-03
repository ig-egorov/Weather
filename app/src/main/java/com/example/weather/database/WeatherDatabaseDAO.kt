package com.example.weather.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.database.entities.CityEntity

@Dao
interface WeatherDatabaseDAO {

    @Insert(entity = CityEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentCity(cityEntity: CityEntity)

    @Query("SELECT * FROM cities_table WHERE cityId = 1")
    fun getCurrentCity(): LiveData<CityEntity>
}