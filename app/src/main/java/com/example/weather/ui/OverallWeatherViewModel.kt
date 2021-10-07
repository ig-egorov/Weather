package com.example.weather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.repository.LocationRepository
import com.example.weather.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class OverallWeatherViewModel(private val mWeatherRepository: WeatherRepository,
                              private val mLocationRepository: LocationRepository) : ViewModel() {

    private val mViewModelJob = SupervisorJob()
    private val mViewModelScope = CoroutineScope(Dispatchers.Main + mViewModelJob)

//    private val mLocationRepository = LocationRepository(application,
//        weatherDatabaseDao)
//    private val mWeatherRepository = WeatherRepository(application, weatherDatabaseDao, )

    init {
        updateWeather()
    }

    val mCurrentCity = mLocationRepository.mCurrentCity
    val mCurrentWeather = mWeatherRepository.mCurrentWeather
    val mHourlyWeather = mWeatherRepository.mHourlyWeather
    val mDailyWeather = mWeatherRepository.mDailyWeather

    private val _navigateToWeatherDetails = MutableLiveData<Int>()
    val navigateToDetails: LiveData<Int>
        get() = _navigateToWeatherDetails

    fun onDayClicked(id: Int) {
        _navigateToWeatherDetails.value = id
    }

    fun onWeatherDetailsNavigated() {
        _navigateToWeatherDetails.value = null
    }

    override fun onCleared() {
        super.onCleared()
        mViewModelJob.cancel()
    }

    fun updateWeather() {
        mViewModelScope.launch {
            mLocationRepository.updateLocation()
            mWeatherRepository.updateWeather()
        }
    }
}