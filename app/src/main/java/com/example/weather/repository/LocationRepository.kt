package com.example.weather.repository

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.weather.R
import com.example.weather.database.WeatherDatabase
import com.example.weather.database.entities.CityEntity
import com.example.weather.database.entities.asDomainModel
import com.example.weather.weather_models.CurrentCity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

private const val TAG = "LocationRepo"

class LocationRepository(
    private val context: Context,
    private val weatherDatabase: WeatherDatabase
) {

    private val _mCurrentCity = Transformations
        .map(weatherDatabase.weatherDatabaseDAO.getCurrentCity()) {
            it?.asDomainModel()
        }

    val mCurrentCity: LiveData<CurrentCity?>
        get() = _mCurrentCity

    suspend fun updateLocation() {
        withContext(Dispatchers.Default) {
            val fusedLocationClient: FusedLocationProviderClient = LocationServices
                .getFusedLocationProviderClient(context)
            val cancellationTokenSource = CancellationTokenSource()
            val geocoder = Geocoder(context)
            val currentLocation = fusedLocationClient
                .awaitCurrentLocation(context, cancellationTokenSource)

            val locationExecutor = Executors.newSingleThreadExecutor()

            val addresses = suspendCoroutine<List<Address>> { continuation ->
                val runnable = Runnable {
                    try {
                        val addresses = geocoder.getFromLocation(
                            currentLocation.latitude,
                            currentLocation.longitude, 1
                        )
                        continuation.resume(addresses)
                    } catch (e: Exception) {
                        ContextCompat.getMainExecutor(context).execute {
                            Toast.makeText(
                                context, context.getString(R.string.location_error),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                }
                locationExecutor.execute(runnable)
            }

            val currentCity = addresses[0].locality

            val currentCityEntity = CityEntity(
                cityName = currentCity,
                latitude = currentLocation.latitude.toString(),
                longitude = currentLocation.longitude.toString()
            )

            withContext(Dispatchers.IO) {
                weatherDatabase.weatherDatabaseDAO.insertCurrentCity(currentCityEntity)
                Log.i(TAG, currentCityEntity.cityName)
                Log.i(TAG, "Weather Inserted")
            }

        }
    }
}

suspend fun FusedLocationProviderClient.awaitCurrentLocation(
    context: Context,
    cancellationTokenSource:
    CancellationTokenSource
): Location =
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
