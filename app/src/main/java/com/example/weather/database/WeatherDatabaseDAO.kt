package com.example.weather.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.database.entities.CityEntity
import com.example.weather.database.entities.CurrentWeatherEntity
import com.example.weather.database.entities.DailyWeatherEntity
import com.example.weather.database.entities.HourlyWeatherEntity

@Dao
interface WeatherDatabaseDAO {

    //CurrentCity methods
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentCity(cityEntity: CityEntity)

    @Query("SELECT * FROM cities_table ORDER BY cityId DESC LIMIT 1")
    fun getCurrentCity(): LiveData<CityEntity?>

    @Query("SELECT * FROM cities_table ORDER BY cityId DESC LIMIT 1")
    suspend fun getCityData(): CityEntity

    //CurrentWeather methods
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeather(currentWeatherEntity: CurrentWeatherEntity)

    @Query("SELECT * FROM current_weather_table ORDER BY currentWeatherId DESC LIMIT 1")
    fun getCurrentWeather(): LiveData<CurrentWeatherEntity?>

    //HourlyWeather methods
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHourlyWeather(vararg hourlyWeatherEntity: HourlyWeatherEntity)

    @Query("SELECT * FROM hourly_weather_table ORDER BY hourlyWeatherId ASC LIMIT 24")
    fun getHourlyWeather(): LiveData<List<HourlyWeatherEntity>>

    //DailyWeather methods
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyWeather(vararg dailyWeatherEntity: DailyWeatherEntity)

    @Query("SELECT * FROM daily_weather_table WHERE dailyWeatherId > 0 ORDER BY dailyWeatherId ASC")
    fun getDailyWeather(): LiveData<List<DailyWeatherEntity>>

    @Query("SELECT * FROM daily_weather_table WHERE dailyWeatherId = :id")
    fun getDailyWeatherWithId(id: Int): LiveData<DailyWeatherEntity>

}