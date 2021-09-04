package com.example.weather.ui

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.weather.database.WeatherDatabase
import com.example.weather.database.entities.asDomainModel
import com.example.weather.weather_models.DailyWeather


class DailyWeatherDetailViewModel(private val database: WeatherDatabase, private val id: Int)
    : ViewModel() {


    var dailyWeather = MediatorLiveData<DailyWeather>()

    init {
        val weather = Transformations.map(database.weatherDatabaseDAO.getDailyWeatherWithId(id)) {
            it.asDomainModel()
        }
        dailyWeather.addSource(weather, dailyWeather::setValue)
    }

}