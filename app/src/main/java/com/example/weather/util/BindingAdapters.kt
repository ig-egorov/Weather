package com.example.weather.util

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.weather.database.entities.CurrentWeatherEntity
import com.example.weather.database.entities.HourlyWeatherEntity
import java.time.ZoneId
import java.util.*
import kotlin.math.roundToInt

@BindingAdapter("bindCurrentTemperature")
fun bindCurrentTemperature(textView: TextView, currentWeatherEntity: CurrentWeatherEntity?) {
    currentWeatherEntity?.let {
        val temperature = currentWeatherEntity.temperature.roundToInt()
        val temperatureString = temperature.toString() + "\u2103"
        textView.text = temperatureString
    }
}

@BindingAdapter("bindCurrentWeatherDescriptionIcon")
fun bindCurrentWeatherDescriptionIcon(imageView: ImageView, currentWeatherEntity: CurrentWeatherEntity?) {
    currentWeatherEntity?.let {
        val icon = currentWeatherEntity.currentWeatherDescriptionIcon
        val imageUrl = "https://openweathermap.org/img/wn/$icon@2x.png"
        val imageUri = imageUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context).load(imageUri).into(imageView)
    }
}

@BindingAdapter("bindHour")
fun bindHour(textView: TextView, hourlyWeatherEntity: HourlyWeatherEntity?) {
    hourlyWeatherEntity?.let {
        val timeInSeconds = hourlyWeatherEntity.date
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInSeconds * 1000
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val hourText = "$hour:00"
        textView.text = hourText
    }
}

@BindingAdapter("bindHourlyWeatherDescriptionIcon")
fun bindHourlyWeatherDescriptionIcon(imageView: ImageView, hourlyWeatherEntity: HourlyWeatherEntity?) {
    hourlyWeatherEntity?.let {
        val icon = hourlyWeatherEntity.hourlyWeatherDescriptionIcon
        val imageUrl = "https://openweathermap.org/img/wn/$icon.png"
        val imageUri = imageUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context).load(imageUri).into(imageView)
    }
}


@BindingAdapter("bindHourlyTemperature")
fun bindHourlyTemperature(textView: TextView, hourlyWeatherEntity: HourlyWeatherEntity?) {
    hourlyWeatherEntity?.let {
        val temperature = hourlyWeatherEntity.temperature.roundToInt()
        val temperatureString = temperature.toString() + "\u2103"
        textView.text = temperatureString
    }
}