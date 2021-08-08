package com.example.weather.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weather.database.WeatherDatabase
import com.example.weather.database.entities.CityEntity
import com.example.weather.database.entities.CurrentWeatherEntity
import com.example.weather.network.WeatherAPI
import com.example.weather.network.asDatabaseModel
import com.example.weather.network.asDomainModel
import kotlinx.coroutines.*

private const val TAG = "CurrentWeatherRepo"

class CurrentWeatherRepository(private val database: WeatherDatabase) {

    private val _mCurrentWeather = database.weatherDatabaseDAO.getCurrentWeather()
    val mCurrentWeather: LiveData<CurrentWeatherEntity>
        get() = _mCurrentWeather

    suspend fun updateCurrentWeather() {
        withContext(Dispatchers.IO) {
                val cityName = database.weatherDatabaseDAO.getCityData()
                val currentWeatherDTO = WeatherAPI.currentWeatherRetrofitService
                    .getCurrentWeather(cityName)
                val currentWeatherEntity = currentWeatherDTO.asDatabaseModel()
                database.weatherDatabaseDAO.insertCurrentWeather(currentWeatherEntity)
        }

    }
}