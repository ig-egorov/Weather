package com.example.weather.di

import android.app.Application
import android.content.Context
import com.example.weather.BaseApplication
import com.example.weather.ui.DailyWeatherDetailFragment
import com.example.weather.ui.OverallWeatherFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(modules = [DatabaseModule::class, WeatherApiModule::class])
interface AppComponent {

    fun inject(overallWeatherFragment: OverallWeatherFragment)
    fun inject(overallWeatherFragment: DailyWeatherDetailFragment) {

    }

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

}