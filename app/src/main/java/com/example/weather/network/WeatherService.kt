package com.example.weather.network

import com.example.weather.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
private const val WEATHER_UNITS = "metric"


private val mMoshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val mRetrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(mMoshi))
    .build()

interface CurrentWeatherApiService {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("units") units: String = WEATHER_UNITS,
        @Query("appid") apiKey:
        String = BuildConfig.CURRENT_WEATHER_API_KEY
    ): CurrentWeatherDTO
}

object WeatherAPI {
    val currentWeatherRetrofitService: CurrentWeatherApiService by lazy {
        mRetrofit.create(CurrentWeatherApiService::class.java)
    }
}
