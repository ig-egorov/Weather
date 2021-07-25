package com.example.weather.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.weather.R
import com.example.weather.databinding.FragmentCurrentWeatherBinding

class CurrentWeatherFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding: FragmentCurrentWeatherBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_current_weather, container, false)
        return binding.root
    }

}