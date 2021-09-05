package com.example.weather.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.weather.R
import com.example.weather.database.WeatherDatabase
import com.example.weather.database.entities.asDomainModel
import com.example.weather.network.WeatherAPI
import com.example.weather.network.asCurrentWeatherDatabaseModel
import com.example.weather.network.asDailyWeatherDatabaseModel
import com.example.weather.network.asHourlyWeatherDatabaseModel
import com.example.weather.weather_models.CurrentWeather
import com.example.weather.weather_models.DailyWeather
import com.example.weather.weather_models.HourlyWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "CurrentWeatherRepo"

class WeatherRepository(private val database: WeatherDatabase) {

    private val _mCurrentWeather = Transformations.map(database.weatherDatabaseDAO.getCurrentWeather()) {
        it?.asDomainModel()
    }
    val mCurrentWeather: LiveData<CurrentWeather?>
        get() = _mCurrentWeather

    private val _mHourlyWeather = Transformations.map(database.weatherDatabaseDAO.getHourlyWeather()) {
        it.asDomainModel()
    }
    val mHourlyWeather: LiveData<List<HourlyWeather>>
        get() = _mHourlyWeather

    private val _mDailyWeather = Transformations.map(database.weatherDatabaseDAO.getDailyWeather()) {
        it.asDomainModel()
    }
    val mDailyWeather: LiveData<List<DailyWeather>>
        get() = _mDailyWeather

    suspend fun updateWeather(context: Context) {
        withContext(Dispatchers.IO) {
            val cityEntity = database.weatherDatabaseDAO.getCityData()
            try {
                val response = WeatherAPI.overallWeatherRetrofitService
                    .getWeather(cityEntity.latitude, cityEntity.longitude)
                if(response.isSuccessful) {
                    val weatherDTO = response.body()!!
                    val currentWeatherEntity = weatherDTO.asCurrentWeatherDatabaseModel()
                    val hourlyWeather = weatherDTO.asHourlyWeatherDatabaseModel()
                    val dailyWeather = weatherDTO.asDailyWeatherDatabaseModel()
                    database.weatherDatabaseDAO.insertCurrentWeather(currentWeatherEntity)
                    database.weatherDatabaseDAO.insertHourlyWeather(*hourlyWeather)
                    database.weatherDatabaseDAO.insertDailyWeather(*dailyWeather)
                } else {
                    Toast.makeText(context, R.string.network_error,
                        Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }
}