package com.example.weather.util

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.weather.R
import com.example.weather.database.entities.CurrentWeatherEntity
import com.example.weather.database.entities.DailyWeatherEntity
import com.example.weather.database.entities.HourlyWeatherEntity
import com.example.weather.weather_models.CurrentCity
import com.example.weather.weather_models.CurrentWeather
import com.example.weather.weather_models.DailyWeather
import com.example.weather.weather_models.HourlyWeather
import java.util.*
import kotlin.math.roundToInt

private const val TEMPERATURE_SIGN = "\u2103"
private const val BASE_URL = "https://openweathermap.org/img/wn/"
private const val EXTENSION = ".png"

@BindingAdapter("bindCurrentTemperature")
fun bindCurrentTemperature(textView: TextView, currentWeather: CurrentWeather?) {
    currentWeather?.let {
        val temperature = currentWeather.temperature.roundToInt()
        val temperatureString = temperature.toString() + TEMPERATURE_SIGN
        textView.text = temperatureString
    }
}

@BindingAdapter("bindCurrentWeatherDescriptionIcon")
fun bindCurrentWeatherDescriptionIcon(imageView: ImageView, currentWeather: CurrentWeather?) {
    currentWeather?.let {
        val icon = currentWeather.currentWeatherDescriptionIcon
        val imageUrl = "$BASE_URL$icon@2x$EXTENSION"
        val imageUri = imageUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context).load(imageUri).into(imageView)
    }
}

@BindingAdapter("bindCurrentCity")
fun bindCurrentCity(textView: TextView, city: CurrentCity?) {
    city?.let {
        val cityName = city.cityName
        textView.text = cityName
    }
}

@BindingAdapter("bindHour")
fun bindHour(textView: TextView, hourlyWeather: HourlyWeather?) {
    hourlyWeather?.let {
        val timeInSeconds = hourlyWeather.date
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInSeconds * 1000L
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val hourText = "$hour:00"
        textView.text = hourText
    }
}

@BindingAdapter("bindHourlyWeatherDescriptionIcon")
fun bindHourlyWeatherDescriptionIcon(imageView: ImageView, hourlyWeather: HourlyWeather?) {
    hourlyWeather?.let {
        val icon = hourlyWeather.hourlyWeatherDescriptionIcon
        val imageUrl = "$BASE_URL$icon$EXTENSION"
        val imageUri = imageUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context).load(imageUri).into(imageView)
    }
}

@BindingAdapter("bindHourlyTemperature")
fun bindHourlyTemperature(textView: TextView, hourlyWeather: HourlyWeather?) {
    hourlyWeather?.let {
        val temperature = hourlyWeather.temperature.roundToInt()
        val temperatureString = temperature.toString() + TEMPERATURE_SIGN
        textView.text = temperatureString
    }
}

@BindingAdapter("bindDailyDay")
fun bindDailyDay(textView: TextView, dailyWeather: DailyWeather?) {
    dailyWeather?.let {
        val dayInSeconds = dailyWeather.date
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = dayInSeconds * 1000L
        val context = textView.context
        val dayOfWeek = when(calendar.get(Calendar.DAY_OF_WEEK)) {
            Calendar.SUNDAY -> context.getString(R.string.sunday)
            Calendar.MONDAY -> context.getString(R.string.monday)
            Calendar.TUESDAY -> context.getString(R.string.tuesday)
            Calendar.WEDNESDAY -> context.getString(R.string.wednesday)
            Calendar.THURSDAY -> context.getString(R.string.thursday)
            Calendar.FRIDAY -> context.getString(R.string.friday)
            else -> context.getString(R.string.saturday)
        }
        textView.text = dayOfWeek
    }
}

@BindingAdapter("bindDailyTemperature")
fun bindDailyTemperature(textView: TextView, dailyWeather: DailyWeather?) {
    dailyWeather?.let {
        val temperature = (dailyWeather.maxTemperature + dailyWeather.minTemperature)/2
        val temperatureString = temperature.roundToInt().toString() + TEMPERATURE_SIGN
        textView.text = temperatureString
    }
}

@BindingAdapter("bindDailyWeatherDescriptionIcon")
fun bindDailyWeatherDescriptionIcon(imageView: ImageView, dailyWeather: DailyWeather?) {
    dailyWeather?.let {
        val icon = dailyWeather.dailyWeatherDescriptionIcon
        val imgUrl = "$BASE_URL$icon$EXTENSION"
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context).load(imgUri).into(imageView)
    }
}

