package com.example.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.HourlyWeatherItemBinding
import com.example.weather.weather_models.HourlyWeather

class HourlyWeatherAdapter : ListAdapter<HourlyWeather,
        HourlyWeatherAdapter.HourlyWeatherViewHolder>(HourlyWeatherDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyWeatherViewHolder {
        return HourlyWeatherViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HourlyWeatherViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class HourlyWeatherViewHolder private constructor(val binding: HourlyWeatherItemBinding) :
                                                            RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HourlyWeather) {
            binding.hourlyWeather = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): HourlyWeatherViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HourlyWeatherItemBinding.inflate(layoutInflater, parent, false)
                return HourlyWeatherViewHolder(binding)
            }
        }
    }

    companion object HourlyWeatherDiffCallback : DiffUtil.ItemCallback<HourlyWeather>() {
        override fun areItemsTheSame(oldItem: HourlyWeather,
                                     newItem: HourlyWeather): Boolean {
            return oldItem.hourlyWeatherId == newItem.hourlyWeatherId
        }

        override fun areContentsTheSame(oldItem: HourlyWeather,
                                        newItem: HourlyWeather): Boolean {
            return oldItem == newItem
        }
    }


}