package com.example.weather.util

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.weather.weather_models.DailyWeather
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

private const val BASE_URL = "https://openweathermap.org/img/wn/"
private const val EXTENSION = ".png"

@BindingAdapter("bindWeatherImage")
fun bindWeatherImage(imageView: ImageView, dailyWeather: DailyWeather?) {
    dailyWeather?.let {
        val icon = dailyWeather.dailyWeatherDescriptionIcon
        val imgUrl = "$BASE_URL$icon@2x$EXTENSION"
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context).load(imgUri).into(imageView)
    }
}

@BindingAdapter("bindMaxTemperature")
fun bindMaxTemperature(textView: TextView, dailyWeather: DailyWeather?) {
    dailyWeather?.let {
        val temperatureString = dailyWeather.maxTemperature.roundToInt().toString()
        textView.text = temperatureString
    }
}

@BindingAdapter("bindMinTemperature")
fun bindMinTemperature(textView: TextView, dailyWeather: DailyWeather?) {
    dailyWeather?.let {
        val temperatureString = dailyWeather.minTemperature.roundToInt().toString()
        textView.text = temperatureString
    }
}

@BindingAdapter("bindWindSpeed")
fun bindWindSpeed(textView: TextView, dailyWeather: DailyWeather?) {
    dailyWeather?.let {
        val windSpeedString = dailyWeather.windSpeed.toString()
        textView.text = windSpeedString
    }
}

@BindingAdapter("bindHumidity")
fun bindHumidity(textView: TextView, dailyWeather: DailyWeather?) {
    dailyWeather?.let {
        val humidityString = dailyWeather.humidity.toString()
        textView.text = humidityString
    }
}

@BindingAdapter("bindSunriseTime")
fun bindSunriseTime(textView: TextView, dailyWeather: DailyWeather?) {
    dailyWeather?.let {
        val sunriseTimeInSeconds = dailyWeather.sunriseTime
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = sunriseTimeInSeconds * 1000L
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val date = dateFormat.format(calendar.timeInMillis).toString()
        textView.text = date
    }
}

@BindingAdapter("bindSunsetTime")
fun bindSunsetTime(textView: TextView, dailyWeather: DailyWeather?) {
    dailyWeather?.let {
        val sunsetTimeInSeconds = dailyWeather.sunsetTime
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = sunsetTimeInSeconds * 1000L
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val date = dateFormat.format(calendar.timeInMillis).toString()
        textView.text = date
    }
}

@BindingAdapter("bindDay")
fun bindDay(textView: TextView, dailyWeather: DailyWeather?) {
    dailyWeather?.let {
        val dateInSeconds = dailyWeather.date
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = dateInSeconds * 1000L
        val dateFormat = SimpleDateFormat("EEEE, d MMMM", Locale.getDefault())
        val date = dateFormat.format(calendar.timeInMillis).toString()
        val sb = StringBuffer(date)
        sb.setCharAt(0, Character.toUpperCase(sb[0]))
        textView.text = sb.toString()
    }
}