package com.example.weather.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.adapters.DailyWeatherAdapter
import com.example.weather.adapters.DailyWeatherClickListener
import com.example.weather.adapters.HourlyWeatherAdapter
import com.example.weather.database.WeatherDatabase
import com.example.weather.databinding.FragmentOverallWeatherBinding

class OverallWeatherFragment : Fragment() {

    private lateinit var viewModel: OverallWeatherViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        val binding: FragmentOverallWeatherBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_overall_weather, container, false)
        setHasOptionsMenu(true)

        val application = requireActivity().application

        val weatherDatabase = WeatherDatabase.getDatabase(application)

        val viewModelFactory = OverallWeatherViewModelFactory(application, weatherDatabase)
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