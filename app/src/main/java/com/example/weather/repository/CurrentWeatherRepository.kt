package com.example.weather.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weather.database.WeatherDatabase
import com.example.weather.database.entities.CityEntity
import com.example.weather.database.entities.CurrentWeatherEntity
import com.example.weather.database.entities.HourlyWeatherEntity
import com.example.weather.network.WeatherAPI
import com.example.weather.network.asCurrentWeatherDatabaseModel
import com.example.weather.network.asHourlyWeatherDatabaseModel
import kotlinx.coroutines.*

private const val TAG = "CurrentWeatherRepo"

class CurrentWeatherRepository(private val database: WeatherDatabase) {

    private val _mCurrentWeather = database.weatherDatabaseDAO.getCurrentWeather()
    val mCurrentWeather: LiveData<CurrentWeatherEntity>
        get() = _mCurrentWeather

    private val _mHourlyWeather = database.weatherDatabaseDAO.getHourlyWeather()
    val mHourlyWeather: LiveData<List<HourlyWeatherEntity>>
        get() = _mHourlyWeather

    suspend fun updateWeather() {
        withContext(Dispatchers.IO) {
                val cityEntity = database.weatherDatabaseDAO.getCityData()
                val weatherDTO = WeatherAPI.overallWeatherRetrofitService
                    .getWeather(cityEntity.latitude, cityEntity.longitude)
                val currentWeatherEntity = weatherDTO.asCurrentWeatherDatabaseModel()
                val hourlyWeather = weatherDTO.asHourlyWeatherDatabaseModel()
                database.weatherDatabaseDAO.insertCurrentWeather(currentWeatherEntity)
                database.weatherDatabaseDAO.insertHourlyWeather(*hourlyWeather)
        }

    }
}