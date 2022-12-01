package com.cool.nicevideo.utils

import com.cool.nicevideo.NiceApp
import com.tencent.mmkv.MMKV

object MMKVUtils {
    init {
        MMKV.initialize(NiceApp.app)
    }

    fun getMMKV() = MMKV.defaultMMKV()
}