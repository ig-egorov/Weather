package com.example.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.database.entities.DailyWeatherEntity
import com.example.weather.databinding.DailyWeatherItemBinding

class DailyWeatherAdapter : ListAdapter<DailyWeatherEntity,
        DailyWeatherAdapter.DailyWeatherViewHolder>(DailyWeatherDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherViewHolder {
        return DailyWeatherViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DailyWeatherViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DailyWeatherViewHolder private constructor(val binding: DailyWeatherItemBinding) :
                                                        RecyclerView.ViewHolder(binding.root){

        fun bind(item: DailyWeatherEntity) {
            binding.dailyWeatherEntity = item
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

    companion object DailyWeatherDiffCallback : DiffUtil.ItemCallback<DailyWeatherEntity>() {
        override fun areItemsTheSame(oldItem: DailyWeatherEntity, newItem: DailyWeatherEntity): Boolean {
            return oldItem.dailyWeatherId == newItem.dailyWeatherId
        }

        override fun areContentsTheSame(oldItem: DailyWeatherEntity, newItem: DailyWeatherEntity): Boolean {
            return oldItem == newItem
        }
    }

}