package com.example.weather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.repository.LocationRepository

class OverallWeatherViewModel : ViewModel() {

    private val _mCurrentTemperature = MutableLiveData<String>()
    val mCurrentTemperature: LiveData<String>
        get() = _mCurrentTemperature

    private val _mCurrentCity = MutableLiveData<String>()
    val mCurrentCity: LiveData<String>
        get() = _mCurrentCity

    init {
        LocationRepository.updateLocation()
        _mCurrentCity.value = LocationRepository.mCurrentCity
    }
}