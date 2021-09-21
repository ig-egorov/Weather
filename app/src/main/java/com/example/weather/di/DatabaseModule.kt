package com.example.weather.di

import android.app.Application
import com.example.weather.database.WeatherDatabase
import com.example.weather.database.WeatherDatabaseDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {


    @Provides
    fun provideDatabase(application: Application): WeatherDatabase =
        WeatherDatabase.getDatabase(application)


    @Provides
    fun provideWeatherDatabaseDao(database: WeatherDatabase): WeatherDatabaseDAO =
        database.weatherDatabaseDAO
}