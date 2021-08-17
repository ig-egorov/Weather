package com.example.weather.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.adapters.DailyWeatherAdapter
import com.example.weather.adapters.HourlyWeatherAdapter
import com.example.weather.database.WeatherDatabase
import com.example.weather.databinding.FragmentOverallWeatherBinding

class OverallWeatherFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding: FragmentOverallWeatherBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_overall_weather, container, false)

        val application = requireActivity().application

        val weatherDatabase = WeatherDatabase.getDatabase(application)

        val viewModelFactory = OverallWeatherViewModelFactory(application, weatherDatabase)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(OverallWeatherViewModel::class.java)

        binding.apply {
            binding.currentWeatherLayout.overallWeatherViewModel = viewModel
        }

        binding.lifecycleOwner = this

        val hourlyWeatherAdapter = HourlyWeatherAdapter()
        binding.hourlyWeatherLayout.hourlyWeatherRecycler.adapter = hourlyWeatherAdapter

        viewModel.mHourlyWeather.observe(viewLifecycleOwner, Observer { it ->
            it?.let {
                hourlyWeatherAdapter.submitList(it)
            }
        })

        val dailyWeatherAdapter = DailyWeatherAdapter()
        binding.dailyWeatherLayout.dailyWeatherRecycler.adapter = dailyWeatherAdapter

        viewModel.mDailyWeather.observe(viewLifecycleOwner, Observer {
            it?.let {
                dailyWeatherAdapter.submitList(it)
            }
        })

        return binding.root
    }

}