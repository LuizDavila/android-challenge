package com.zygotecnologia.zygotv.monitor

import android.net.wifi.p2p.WifiP2pDevice.CONNECTED


abstract class ConnectivityProviderBaseImpl : ConnectivityProvider {
    override fun subscribe() {
        subscribeListener()
        dispatchChange(getNetworkState())
    }

    protected fun dispatchChange(state: ConnectivityProvider.NetworkState) {
        val networkState = if (state.hasInternet()) CONNECTED else DISCONNECTED
        if (networkState != NetWorkManger.networkStatus.value) {
            NetWorkManger.networkStatus.postValue(networkState)
        }
    }

    private fun ConnectivityProvider.NetworkState.hasInternet(): Boolean {
        return (this as? ConnectivityProvider.NetworkState.ConnectedState)?.hasInternet == true
    }

    protected abstract fun subscribeListener()
}
