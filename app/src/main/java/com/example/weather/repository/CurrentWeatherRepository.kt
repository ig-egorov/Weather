package com.example.weather.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weather.database.WeatherDatabase
import com.example.weather.database.entities.CityEntity
import com.example.weather.database.entities.CurrentWeatherEntity
import com.example.weather.network.WeatherAPI
import com.example.weather.network.asCurrentWeatherDatabaseModel
import kotlinx.coroutines.*

private const val TAG = "CurrentWeatherRepo"

class CurrentWeatherRepository(private val database: WeatherDatabase) {

    private val _mCurrentWeather = database.weatherDatabaseDAO.getCurrentWeather()
    val mCurrentWeather: LiveData<CurrentWeatherEntity>
        get() = _mCurrentWeather

    suspend fun updateCurrentWeather() {
        withContext(Dispatchers.IO) {
                val cityEntity = database.weatherDatabaseDAO.getCityData()
                val currentWeatherDTO = WeatherAPI.overallWeatherRetrofitService
                    .getCurrentWeather(cityEntity.latitude, cityEntity.longitude)
                val currentWeatherEntity = currentWeatherDTO.asCurrentWeatherDatabaseModel()
                database.weatherDatabaseDAO.insertCurrentWeather(currentWeatherEntity)
        }

    }
}