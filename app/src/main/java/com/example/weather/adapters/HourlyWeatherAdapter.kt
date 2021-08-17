package com.example.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.database.entities.HourlyWeatherEntity
import com.example.weather.databinding.HourlyWeatherItemBinding

class HourlyWeatherAdapter : ListAdapter<HourlyWeatherEntity,
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

        fun bind(item: HourlyWeatherEntity) {
            binding.hourlyWeatherEntity = item
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

    companion object HourlyWeatherDiffCallback : DiffUtil.ItemCallback<HourlyWeatherEntity>() {
        override fun areItemsTheSame(oldItem: HourlyWeatherEntity,
                                     newItem: HourlyWeatherEntity): Boolean {
            return oldItem.hourlyWeatherId == newItem.hourlyWeatherId
        }

        override fun areContentsTheSame(oldItem: HourlyWeatherEntity,
                                        newItem: HourlyWeatherEntity): Boolean {
            return oldItem == newItem
        }
    }


}