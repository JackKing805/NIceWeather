package com.cool.nicevideo.ui.pages.home

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.scopeNetLife
import androidx.lifecycle.viewModelScope
import com.cool.nicevideo.model.DataWeather
import com.cool.nicevideo.utils.LocationUtils
import com.cool.nicevideo.utils.LocationUtilsKTX
import com.cool.nicevideo.utils.MMKVUtils
import com.drake.net.Get
import com.google.gson.Gson
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


data class HomeUIState(
    val location: HomeLocation = HomeLocation.Loading
)

sealed class HomeLocation{
    data class Location(
        val city:String,
        val location: LocationUtilsKTX.Location,
        val dataWeather: DataWeather
    ):HomeLocation()

    object GrantPermission:HomeLocation()

    object Loading:HomeLocation()
}


class HomeViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
    val uiState = MutableStateFlow(
        HomeUIState()
    )

    init {
        viewModelScope.launch {
            if (XXPermissions.isGranted(application,Permission.ACCESS_FINE_LOCATION,Permission.ACCESS_COARSE_LOCATION)){
                delay(2000)
                getNetworkLocation(application)
            }else{
                uiState.value = uiState.value.copy(location = HomeLocation.GrantPermission)
            }
        }
    }

    private var isRequestLocationPermission = false
    fun requireLocationPermission(context: Context) {
        if (XXPermissions.isGranted(context,Permission.ACCESS_FINE_LOCATION,Permission.ACCESS_COARSE_LOCATION)){
            return
        }
        if (isRequestLocationPermission){
            return
        }
        isRequestLocationPermission = true
        XXPermissions.with(context)
            .permission(Permission.ACCESS_FINE_LOCATION, Permission.ACCESS_COARSE_LOCATION)
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>?, all: Boolean) {
                    if (all) {
                        getNetworkLocation(context)
                    }
                    isRequestLocationPermission = false
                }

                override fun onDenied(permissions: MutableList<String>?, never: Boolean) {
                    if (never) {
                        XXPermissions.startPermissionActivity(context)
                    }
                }
            })
    }

    private fun getNetworkLocation(context: Context){
        scopeNetLife {
            uiState.value = uiState.value.copy(location = HomeLocation.Loading)
            val location = LocationUtilsKTX.getLocation(context)
            val address = LocationUtils.getAddress(location.Latitude, location.Longitude)

            val now = System.currentTimeMillis()
            val dis = now - MMKVUtils.getMMKV().decodeLong("last_weather_time",now)
            val get = if (dis==0L || dis >= 1000L*60*60*24){
                val searchCity = address.locality.toString().replace("市","")
                val json = Get<String>("https://v0.yiketianqi.com/api/worldchina?appid=71787996&appsecret=qz8ofOM4&city=${searchCity}").await()
                MMKVUtils.getMMKV().encode("last_weather_time",System.currentTimeMillis())
                MMKVUtils.getMMKV().encode("last_weather_json",json)
                json
            }else{
                MMKVUtils.getMMKV().decodeString("last_weather_json")
            }
            val dataWeather = Gson().fromJson(get,DataWeather::class.java)
            uiState.value = uiState.value.copy(location = HomeLocation.Location(address.locality,location,dataWeather))
        }
    }
}