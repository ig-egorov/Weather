package com.example.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weather.R
import com.example.weather.database.WeatherDatabase
import com.example.weather.databinding.FragmentDailyWeatherDetailBinding

class DailyWeatherDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentDailyWeatherDetailBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_daily_weather_detail, container, false)
        binding.lifecycleOwner = this

        val application = requireActivity().application

        val args = DailyWeatherDetailFragmentArgs.fromBundle(requireArguments())

        val database = WeatherDatabase.getDatabase(application)

        val viewModelFactory = DailyWeatherDetailViewModelFactory(database, args.weatherId)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(DailyWeatherDetailViewModel::class.java)

        binding.viewModel = viewModel

        return binding.root
    }

}