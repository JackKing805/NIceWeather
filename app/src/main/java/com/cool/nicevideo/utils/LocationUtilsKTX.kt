package com.cool.nicevideo.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions

object LocationUtilsKTX {
    @SuppressLint("MissingPermission")
    fun getLocation(context: Context):Location{
        if (XXPermissions.isGranted(context,Permission.ACCESS_COARSE_LOCATION,Permission.ACCESS_FINE_LOCATION)){
            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val providers = locationManager.getProviders(true)
            providers.forEach {
                val  location = locationManager.getLastKnownLocation(it)
                if (location!=null){
                    return Location(
                        location.longitude,
                        location.latitude
                    )
                }
            }
        }
        return Location(-1.0,-1.0)
    }


    data class Location(
        val Longitude:Double,
        val Latitude:Double
    )
}