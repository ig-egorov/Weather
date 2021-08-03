package com.example.weather.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.database.WeatherDatabase
import com.example.weather.database.WeatherDatabaseDAO
import java.lang.IllegalArgumentException

class OverallWeatherViewModelFactory(private val application: Application,
                                     private val weatherDatabase: WeatherDatabase) :
    ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OverallWeatherViewModel::class.java)) {
            return OverallWeatherViewModel(application, weatherDatabase) as T
        }
        throw (IllegalArgumentException("Unknown ViewModel Class"))
    }
}