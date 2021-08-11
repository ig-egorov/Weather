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
import com.example.weather.database.WeatherDatabase
import com.example.weather.database.entities.CityEntity
import com.google.android.gms.location.*
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

private const val TAG = "LocationRepo"

class LocationRepository(private val context: Context,
                         private val weatherDatabase: WeatherDatabase
) {

    private val _mCurrentCity = weatherDatabase.weatherDatabaseDAO.getCurrentCity()
    val mCurrentCity: LiveData<CityEntity>
        get() = _mCurrentCity

    suspend fun updateLocation() {
        withContext(Dispatchers.Default) {
            val fusedLocationClient: FusedLocationProviderClient = LocationServices
                .getFusedLocationProviderClient(context)
            val cancellationTokenSource = CancellationTokenSource()

            val currentLocation = fusedLocationClient
                .awaitCurrentLocation(context, cancellationTokenSource)

            val geocoder = Geocoder(context)

            val addresses = geocoder.getFromLocation(currentLocation.latitude,
                currentLocation.longitude, 1)

            val currentCity = addresses[0].locality

            val currentCityEntity = CityEntity(cityName = currentCity,
                latitude = currentLocation.latitude.toString(),
                longitude = currentLocation.longitude.toString())

            withContext(Dispatchers.IO) {
                weatherDatabase.weatherDatabaseDAO.insertCurrentCity(currentCityEntity)
                Log.i(TAG, "${currentCityEntity.cityName}")
                Log.i(TAG, "Weather Inserted")
            }
        }
    }
}

suspend fun FusedLocationProviderClient.awaitCurrentLocation(context: Context,
                                                             cancellationTokenSource:
                                                             CancellationTokenSource): Location =
    suspendCancellableCoroutine { continuation ->
        if (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            getCurrentLocation(
                LocationRequest.PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource.token
            )
                .addOnSuccessListener { location ->
                    continuation.resume(location)
                }.addOnFailureListener { exception ->
                    continuation.resumeWithException(exception)
                }
        }
    }