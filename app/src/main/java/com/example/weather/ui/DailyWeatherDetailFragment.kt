package com.example.weather.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weather.R
import com.example.weather.appComponent
import com.example.weather.database.WeatherDatabaseDAO
import com.example.weather.databinding.FragmentDailyWeatherDetailBinding
import javax.inject.Inject

class DailyWeatherDetailFragment : Fragment() {

    @Inject
    lateinit var weatherDatabaseDAO: WeatherDatabaseDAO

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentDailyWeatherDetailBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_daily_weather_detail, container, false)
        binding.lifecycleOwner = this

        val args = DailyWeatherDetailFragmentArgs.fromBundle(requireArguments())

        val viewModelFactory = DailyWeatherDetailViewModelFactory(weatherDatabaseDAO, args.weatherId)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(DailyWeatherDetailViewModel::class.java)

        binding.viewModel = viewModel

        return binding.root
    }

}