package com.example.weather.repository

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.*
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task
import java.util.*

const val TAG = "LocationRepository"

class LocationRepository(private val context: Context) {

    private var _mCurrentCity = MutableLiveData<String>()
    val mCurrentCity: LiveData<String>
        get() = _mCurrentCity

    fun updateLocation() {
        val fusedLocationClient: FusedLocationProviderClient = LocationServices
            .getFusedLocationProviderClient(context)
        val cancellationTokenSource = CancellationTokenSource()

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Log.i(TAG, "PERMISSION GRANTED")
            val currentLocationTask: Task<Location> = fusedLocationClient.getCurrentLocation(
                LocationRequest.PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource.token
            )

            currentLocationTask.addOnCompleteListener { task: Task<Location> ->
                if (task.isSuccessful && task.result != null) {
                    val geocoder = Geocoder(context)
                    val addresses = geocoder
                        .getFromLocation(task.result.latitude, task.result.longitude, 1)
                    val overallCityInformation = addresses[0]
                    val currentCity = overallCityInformation.locality
                    Log.i(TAG, "${currentCity}")
                    _mCurrentCity.value = currentCity
                } else {
                    Log.i(TAG, "${task.exception}")
                    return@addOnCompleteListener
                }
            }
        } 
    }
}