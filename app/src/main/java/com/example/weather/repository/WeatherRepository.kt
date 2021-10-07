package com.example.weather.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.weather.R
import com.example.weather.database.WeatherDatabaseDAO
import com.example.weather.database.entities.asDomainModel
import com.example.weather.network.WeatherApiService
import com.example.weather.network.asCurrentWeatherDatabaseModel
import com.example.weather.network.asDailyWeatherDatabaseModel
import com.example.weather.network.asHourlyWeatherDatabaseModel
import com.example.weather.weather_models.CurrentWeather
import com.example.weather.weather_models.DailyWeather
import com.example.weather.weather_models.HourlyWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "CurrentWeatherRepo"

@Singleton
class WeatherRepository @Inject constructor(private val application: Application,
                                            private val weatherDatabaseDAO: WeatherDatabaseDAO,
                                            private val weatherApiService: WeatherApiService) {

    private val _mCurrentWeather = Transformations.map(weatherDatabaseDAO.getCurrentWeather()) {
        it?.asDomainModel()
    }
    val mCurrentWeather: LiveData<CurrentWeather?>
        get() = _mCurrentWeather

    private val _mHourlyWeather = Transformations.map(weatherDatabaseDAO.getHourlyWeather()) {
        it.asDomainModel()
    }
    val mHourlyWeather: LiveData<List<HourlyWeather>>
        get() = _mHourlyWeather

    private val _mDailyWeather = Transformations.map(weatherDatabaseDAO.getDailyWeather()) {
        it.asDomainModel()
    }
    val mDailyWeather: LiveData<List<DailyWeather>>
        get() = _mDailyWeather

    suspend fun updateWeather() {
        withContext(Dispatchers.IO) {
            val cityEntity = weatherDatabaseDAO.getCityData()
            try {
                val response = weatherApiService.getWeather(cityEntity.latitude, cityEntity.longitude)
                if(response.isSuccessful) {
                    val weatherDTO = response.body()!!
                    val currentWeatherEntity = weatherDTO.asCurrentWeatherDatabaseModel()
                    val hourlyWeather = weatherDTO.asHourlyWeatherDatabaseModel()
                    val dailyWeather = weatherDTO.asDailyWeatherDatabaseModel()
                    weatherDatabaseDAO.apply {
                        insertCurrentWeather(currentWeatherEntity)
                        insertHourlyWeather(*hourlyWeather)
                        insertDailyWeather(*dailyWeather)
                    }
                } else {
                    Toast.makeText(application, R.string.network_error,
                        Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}