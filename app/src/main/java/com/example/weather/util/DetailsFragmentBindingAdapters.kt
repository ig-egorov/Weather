package com.example.weather.util

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.weather.weather_models.DailyWeather
import kotlin.math.roundToInt

private const val BASE_URL = "https://openweathermap.org/img/wn/"
private const val EXTENSION = ".png"

@BindingAdapter("bindWeatherImage")
fun bindWeatherImage(imageView: ImageView, dailyWeather: DailyWeather?) {
    dailyWeather?.let {
        val icon = dailyWeather.dailyWeatherDescriptionIcon
        val imgUrl = "$BASE_URL$icon$EXTENSION"
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

    }
}

@BindingAdapter("bindSunsetTime")
fun bindSunsetTime(textView: TextView, dailyWeather: DailyWeather?) {
    dailyWeather?.let {

    }
}

fun bindDay(textView: TextView, dailyWeather: DailyWeather?) {
    dailyWeather?.let {

    }
}