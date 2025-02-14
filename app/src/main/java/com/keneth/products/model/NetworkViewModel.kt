package com.keneth.products.model

import androidx.lifecycle.ViewModel
import com.keneth.products.utility.NetworkMonitor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NetworkViewModel(private val networkMonitor: NetworkMonitor) : ViewModel() {
    private val _isConnected = MutableStateFlow(true)
    val isConnected = _isConnected.asStateFlow()

    init {
        checkInitialConnection()
    }

    fun checkInitialConnection() {
        _isConnected.value = networkMonitor.currentStatus
    }

    fun updateConnection(status: Boolean) {
        _isConnected.value = status
    }
}