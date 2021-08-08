package com.example.weather.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.database.WeatherDatabase
import com.example.weather.database.WeatherDatabaseDAO
import com.example.weather.repository.CurrentWeatherRepository
import com.example.weather.repository.LocationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class OverallWeatherViewModel(val application: Application,
                              val weatherDatabase: WeatherDatabase
) : ViewModel() {

    private val mViewModelJob = SupervisorJob()
    private val mViewModelScope = CoroutineScope(Dispatchers.Main + mViewModelJob)

    private val mLocationRepository = LocationRepository(application.applicationContext,
        weatherDatabase)
    private val mCurrentWeatherRepository = CurrentWeatherRepository(weatherDatabase)


    init {
        mViewModelScope.launch {
            mLocationRepository.updateLocation()
            //val city = weatherDatabase.weatherDatabaseDAO.getCurrentCity()
            mCurrentWeatherRepository.updateCurrentWeather()
        }
    }

    val mCurrentCity = mLocationRepository.mCurrentCity
    val mCurrentWeather = mCurrentWeatherRepository.mCurrentWeather

    override fun onCleared() {
        super.onCleared()
        mViewModelJob.cancel()
    }
}