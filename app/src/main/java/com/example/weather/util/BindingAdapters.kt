package com.example.weather.util

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.weather.database.entities.CurrentWeatherEntity
import kotlin.math.roundToInt

@BindingAdapter("bindCurrentTemperature")
fun bindCurrentTemperature(textView: TextView, currentWeatherEntity: CurrentWeatherEntity?) {
    currentWeatherEntity?.let {
        val temperature = currentWeatherEntity.temperature.roundToInt()
        val temperatureString = temperature.toString() + "\u2103"
        textView.text = temperatureString
    }
}

@BindingAdapter("bindWeatherDescriptionIcon")
fun bindWeatherDescriptionIcon(imageView: ImageView, icon: String?) {
    icon?.let {
        val imageUrl = "https://openweathermap.org/img/wn/$icon@2x.png"
        val imageUri = imageUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context).load(imageUri).into(imageView)
    }
}