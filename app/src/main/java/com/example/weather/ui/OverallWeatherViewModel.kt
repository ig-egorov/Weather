package com.example.weather.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.repository.LocationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class OverallWeatherViewModel(val application: Application) : ViewModel() {

    private val mViewModelJob = SupervisorJob()
    private val mViewModelScope = CoroutineScope(Dispatchers.Main + mViewModelJob)

    private val mLocationRepository = LocationRepository(application.applicationContext)

    init {
        mViewModelScope.launch {
            mLocationRepository.updateLocation()
        }
    }

    private val _mCurrentTemperature = MutableLiveData<String>()
    val mCurrentTemperature: LiveData<String>
        get() = _mCurrentTemperature

    val mCurrentCity = mLocationRepository.mCurrentCity

}