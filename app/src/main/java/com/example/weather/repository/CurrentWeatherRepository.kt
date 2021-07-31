package com.example.weather.repository

import androidx.lifecycle.MutableLiveData
import com.example.weather.network.WeatherAPI
import com.example.weather.network.asDomainModel
import kotlinx.coroutines.*

class CurrentWeatherRepository {

    val mCurrentTemperature = MutableLiveData<String>()

    suspend fun getCurrentWeather(): String {
        return withContext(Dispatchers.IO) {
            val currentWeatherDTO = WeatherAPI.currentWeatherRetrofitService
                .getCurrentWeather("Moscow")
            val currentWeather = currentWeatherDTO.asDomainModel()
            currentWeather.currentWeatherConditionsInfo.temperature
                .toString()
        }
    }
}