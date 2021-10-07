package com.example.weather.network

import com.example.weather.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val WEATHER_UNITS = "metric"
private const val EXCLUDE_STRING = "minutely,alerts"


interface WeatherApiService {
    @GET("onecall")
    suspend fun getWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String = WEATHER_UNITS,
        @Query("exclude") exclude: String = EXCLUDE_STRING,
        @Query("appid") apiKey: String = BuildConfig.CURRENT_WEATHER_API_KEY
    ): Response<OverallWeatherDTO>

    companion object {
        val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }
}

