package com.example.weather

import android.app.Application
import android.content.Context
import com.example.weather.di.AppComponent
import com.example.weather.di.DaggerAppComponent

class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        appComponent = DaggerAppComponent.factory().create(this)
        super.onCreate()
    }
}

val Context.appComponent: AppComponent
    get() = when(this) {
        is BaseApplication -> appComponent
        else -> this.applicationContext.appComponent
    }