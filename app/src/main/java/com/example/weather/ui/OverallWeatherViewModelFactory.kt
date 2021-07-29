package com.example.weather.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class OverallWeatherViewModelFactory(private val application: Application) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OverallWeatherViewModel::class.java)) {
            return OverallWeatherViewModel(application) as T
        }
        throw (IllegalArgumentException("Unknown ViewModel Class"))
    }
}