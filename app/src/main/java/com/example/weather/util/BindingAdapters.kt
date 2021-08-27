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
import java.util.*
import kotlin.math.roundToInt

private const val TEMPERATURE_SIGN = "\u2103"
private const val BASE_URL = "https://openweathermap.org/img/wn/"
private const val EXTENSION = ".png"

@BindingAdapter("bindCurrentTemperature")
fun bindCurrentTemperature(textView: TextView, currentWeatherEntity: CurrentWeatherEntity?) {
    currentWeatherEntity?.let {
        val temperature = currentWeatherEntity.temperature.roundToInt()
        val temperatureString = temperature.toString() + TEMPERATURE_SIGN
        textView.text = temperatureString
    }
}

@BindingAdapter("bindCurrentWeatherDescriptionIcon")
fun bindCurrentWeatherDescriptionIcon(imageView: ImageView, currentWeatherEntity: CurrentWeatherEntity?) {
    currentWeatherEntity?.let {
        val icon = currentWeatherEntity.currentWeatherDescriptionIcon
        val imageUrl = "$BASE_URL$icon@2x$EXTENSION"
        val imageUri = imageUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context).load(imageUri).into(imageView)
    }
}

@BindingAdapter("bindHour")
fun bindHour(textView: TextView, hourlyWeatherEntity: HourlyWeatherEntity?) {
    hourlyWeatherEntity?.let {
        val timeInSeconds = hourlyWeatherEntity.date
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInSeconds * 1000L
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val hourText = "$hour:00"
        textView.text = hourText
    }
}

@BindingAdapter("bindHourlyWeatherDescriptionIcon")
fun bindHourlyWeatherDescriptionIcon(imageView: ImageView, hourlyWeatherEntity: HourlyWeatherEntity?) {
    hourlyWeatherEntity?.let {
        val icon = hourlyWeatherEntity.hourlyWeatherDescriptionIcon
        val imageUrl = "$BASE_URL$icon$EXTENSION"
        val imageUri = imageUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context).load(imageUri).into(imageView)
    }
}


@BindingAdapter("bindHourlyTemperature")
fun bindHourlyTemperature(textView: TextView, hourlyWeatherEntity: HourlyWeatherEntity?) {
    hourlyWeatherEntity?.let {
        val temperature = hourlyWeatherEntity.temperature.roundToInt()
        val temperatureString = temperature.toString() + TEMPERATURE_SIGN
        textView.text = temperatureString
    }
}

@BindingAdapter("bindDailyDay")
fun bindDailyDay(textView: TextView, dailyWeatherEntity: DailyWeatherEntity?) {
    dailyWeatherEntity?.let {
        val dayInSeconds = dailyWeatherEntity.date
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
fun bindDailyTemperature(textView: TextView, dailyWeatherEntity: DailyWeatherEntity?) {
    dailyWeatherEntity?.let {
        val temperature = (dailyWeatherEntity.maxTemperature + dailyWeatherEntity.minTemperature)/2
        val temperatureString = temperature.roundToInt().toString() + TEMPERATURE_SIGN
        textView.text = temperatureString
    }
}

@BindingAdapter("bindDailyWeatherDescriptionIcon")
fun bindDailyWeatherDescriptionIcon(imageView: ImageView, dailyWeatherEntity: DailyWeatherEntity?) {
    dailyWeatherEntity?.let {
        val icon = dailyWeatherEntity.dailyWeatherDescriptionIcon
        val imgUrl = "$BASE_URL$icon$EXTENSION"
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context).load(imgUri).into(imageView)
    }
}