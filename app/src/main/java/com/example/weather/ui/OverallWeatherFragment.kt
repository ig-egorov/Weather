package com.example.weather.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.weather.R
import com.example.weather.adapters.DailyWeatherAdapter
import com.example.weather.adapters.DailyWeatherClickListener
import com.example.weather.adapters.HourlyWeatherAdapter
import com.example.weather.appComponent
import com.example.weather.databinding.FragmentOverallWeatherBinding
import com.example.weather.repository.LocationRepository
import com.example.weather.repository.WeatherRepository
import javax.inject.Inject

class OverallWeatherFragment : Fragment() {

    @Inject
    lateinit var locationRepository: LocationRepository
    @Inject
    lateinit var weatherRepository: WeatherRepository

    private lateinit var viewModel: OverallWeatherViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: FragmentOverallWeatherBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_overall_weather, container, false)
        setHasOptionsMenu(true)

        val viewModelFactory = OverallWeatherViewModelFactory(weatherRepository, locationRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(OverallWeatherViewModel::class.java)

        binding.apply {
            currentWeatherLayout.overallWeatherViewModel = viewModel
        }

        binding.lifecycleOwner = this

        val hourlyWeatherAdapter = HourlyWeatherAdapter()
        binding.hourlyWeatherLayout.hourlyWeatherRecycler.adapter = hourlyWeatherAdapter

        viewModel.mHourlyWeather.observe(viewLifecycleOwner, Observer { it ->
            it?.let {
                hourlyWeatherAdapter.submitList(it)
            }
        })

        val dailyWeatherAdapter = DailyWeatherAdapter(DailyWeatherClickListener { dailyWeatherId ->
            viewModel.onDayClicked(dailyWeatherId)
        })
        binding.dailyWeatherLayout.dailyWeatherRecycler.adapter = dailyWeatherAdapter

        viewModel.mDailyWeather.observe(viewLifecycleOwner, Observer {
            it?.let {
                dailyWeatherAdapter.submitList(it)
            }
        })

        viewModel.navigateToDetails.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController().navigate(OverallWeatherFragmentDirections
                    .actionOverallWeatherFragmentToDailyWeatherDetailFragment(it))
                viewModel.onWeatherDetailsNavigated()
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.update -> {
                viewModel.updateWeather()
                true
            }
            else -> false
        }
    }
}