package com.example.weather.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weather.R
import com.example.weather.database.WeatherDatabase
import com.example.weather.database.entities.CityEntity
import com.example.weather.database.entities.CurrentWeatherEntity
import com.example.weather.database.entities.DailyWeatherEntity
import com.example.weather.database.entities.HourlyWeatherEntity
import com.example.weather.network.WeatherAPI
import com.example.weather.network.asCurrentWeatherDatabaseModel
import com.example.weather.network.asDailyWeatherDatabaseModel
import com.example.weather.network.asHourlyWeatherDatabaseModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

private const val TAG = "CurrentWeatherRepo"

class CurrentWeatherRepository(private val database: WeatherDatabase) {

    private val _mCurrentWeather = database.weatherDatabaseDAO.getCurrentWeather()
    val mCurrentWeather: LiveData<CurrentWeatherEntity>
        get() = _mCurrentWeather

    private val _mHourlyWeather = database.weatherDatabaseDAO.getHourlyWeather()
    val mHourlyWeather: LiveData<List<HourlyWeatherEntity>>
        get() = _mHourlyWeather

    private val _mDailyWeather = database.weatherDatabaseDAO.getDailyWeather()
    val mDailyWeather: LiveData<List<DailyWeatherEntity>>
        get() = _mDailyWeather

    suspend fun updateWeather(context: Context) {
        withContext(Dispatchers.IO) {
            val cityEntity = database.weatherDatabaseDAO.getCityData()
            val response = WeatherAPI.overallWeatherRetrofitService
                    .getWeather(cityEntity.latitude, cityEntity.longitude)
            if(response.isSuccessful) {
                val weatherDTO = response.body()!!
                val currentWeatherEntity = weatherDTO.asCurrentWeatherDatabaseModel()
                val hourlyWeather = weatherDTO.asHourlyWeatherDatabaseModel()
                val dailyWeather = weatherDTO.asDailyWeatherDatabaseModel()
                database.weatherDatabaseDAO.insertCurrentWeather(currentWeatherEntity)
                database.weatherDatabaseDAO.insertHourlyWeather(*hourlyWeather)
                database.weatherDatabaseDAO.insertDailyWeather(*dailyWeather)
            } else {
                Toast.makeText(context, R.string.network_error,
                    Toast.LENGTH_SHORT).show()
            }
        }

    }
}