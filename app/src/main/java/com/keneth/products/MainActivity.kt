package com.keneth.products

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.keneth.products.model.NetworkViewModel
import com.keneth.products.screen.ProductMainApp
import com.keneth.products.ui.theme.FakeProductsTheme
import com.keneth.products.utility.NetworkMonitor
import com.keneth.products.utility.NetworkStatusBanner


class MainActivity : ComponentActivity() {
    private lateinit var networkMonitor: NetworkMonitor
    private val viewModel by viewModels<NetworkViewModel> {
        object : ViewModelProvider.Factory, ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                // Initialize NetworkMonitor first
                networkMonitor = NetworkMonitor(this@MainActivity)
                return NetworkViewModel(networkMonitor) as T
            }
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize network monitor early
        networkMonitor = NetworkMonitor(this)

        enableEdgeToEdge()
        setContent {
            FakeProductsTheme {
                val connectionState by viewModel.isConnected.collectAsState()

                Box(modifier = Modifier.fillMaxSize()) {
                    // Always show main content
                    Scaffold {
                        ProductMainApp()
                    }

                    // Overlay network status
                    if (!connectionState) {
                        NetworkStatusBanner(viewModel)
                    }
                }
            }
        }
        registerNetworkCallback()
    }

    private fun registerNetworkCallback() {
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                runOnUiThread { viewModel.updateConnection(true) }
            }

            override fun onLost(network: Network) {
                runOnUiThread { viewModel.updateConnection(false) }
            }
        }
        networkMonitor.connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }
}
