package com.zygotecnologia.zygotv.monitor

import androidx.lifecycle.MutableLiveData

const val DISCONNECTED = -4
const val CONNECTED = 0

object NetWorkManger {
    val networkStatus = MutableLiveData<Int>()
    fun isDisconnected(): Boolean {
        return networkStatus.value == DISCONNECTED
    }
}

class NetWorkDisconnectedException : Throwable()
