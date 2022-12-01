package com.cool.nicevideo

import android.app.Application
import com.tencent.mmkv.MMKV
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NiceApp: Application() {
    companion object{
        lateinit var app:Application
    }

    init {
        app = this
    }


    override fun onCreate() {
        super.onCreate()
    }
}