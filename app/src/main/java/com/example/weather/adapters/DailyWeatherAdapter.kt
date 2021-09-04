package com.example.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.database.entities.DailyWeatherEntity
import com.example.weather.databinding.DailyWeatherItemBinding
import com.example.weather.weather_models.DailyWeather

class DailyWeatherAdapter(private val clickListener: DailyWeatherClickListener) : ListAdapter<DailyWeather,
        DailyWeatherAdapter.DailyWeatherViewHolder>(DailyWeatherDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherViewHolder {
        return DailyWeatherViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DailyWeatherViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }

    class DailyWeatherViewHolder private constructor(val binding: DailyWeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: DailyWeatherClickListener, item: DailyWeather) {
            binding.dailyWeather = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): DailyWeatherViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DailyWeatherItemBinding.inflate(layoutInflater, parent, false)
                return DailyWeatherViewHolder(binding)
            }
        }
    }

    companion object DailyWeatherDiffCallback : DiffUtil.ItemCallback<DailyWeather>() {
        override fun areItemsTheSame(
            oldItem: DailyWeather,
            newItem: DailyWeather
        ): Boolean {
            return oldItem.dailyWeatherId == newItem.dailyWeatherId
        }

        override fun areContentsTheSame(
            oldItem: DailyWeather,
            newItem: DailyWeather
        ): Boolean {
            return oldItem == newItem
        }
    }

}

class DailyWeatherClickListener(val clickListener: (dailyWeatherId: Int) -> Unit) {
    fun onDailyWeatherClick(dailyWeather: DailyWeather) =
        clickListener(dailyWeather.dailyWeatherId)
}