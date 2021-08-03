package com.example.weather.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weather.database.entities.CityEntity

@Database(entities = [CityEntity::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract val weatherDatabaseDAO: WeatherDatabaseDAO

    companion object {
        @Volatile
        private lateinit var INSTANCE: WeatherDatabase

        fun getDatabase(context: Context): WeatherDatabase {
            if (!::INSTANCE.isInitialized) {
                synchronized(this) {
                    if (!::INSTANCE.isInitialized) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                            WeatherDatabase::class.java,"weather_database")
                            .fallbackToDestructiveMigration().build()
                    }
                }
            }
            return INSTANCE
        }
    }

}