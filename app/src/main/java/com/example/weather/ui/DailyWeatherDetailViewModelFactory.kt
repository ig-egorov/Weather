package com.example.weather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.database.WeatherDatabase

class DailyWeatherDetailViewModelFactory(private val database: WeatherDatabase, private val id: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DailyWeatherDetailViewModel::class.java)) {
            return DailyWeatherDetailViewModel(database, id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}