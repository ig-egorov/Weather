package com.example.weather.repository

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weather.MainActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception
import java.util.*

class LocationRepository {

    companion object {

        var mCurrentCity: String = "None"

        private val context = Application().applicationContext

        private val fusedLocationClient: FusedLocationProviderClient by lazy {
            LocationServices.getFusedLocationProviderClient(context)
        }

        fun updateLocation() {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                fusedLocationClient.lastLocation.addOnCompleteListener {
                    val location: Location = it.result

                    try {
                        val geocoder: Geocoder = Geocoder(context, Locale.getDefault())
                        val adresses: List<Address> = geocoder.getFromLocation(
                            location.latitude, location.longitude, 1
                        )
                        mCurrentCity = adresses[0].locality
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}