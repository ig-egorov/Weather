package com.example.weather.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.database.WeatherDatabase
import com.example.weather.database.WeatherDatabaseDAO
import com.example.weather.repository.LocationRepository
import com.example.weather.repository.WeatherRepository

class OverallWeatherViewModelFactory(private val weatherRepository: WeatherRepository,
                                     private val locationRepository: LocationRepository) :
    ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OverallWeatherViewModel::class.java)) {
            return OverallWeatherViewModel(weatherRepository, locationRepository) as T
        }
        throw (IllegalArgumentException("Unknown ViewModel Class"))
    }
}