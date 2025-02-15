package com.keneth.products.utility

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.keneth.products.model.NetworkViewModel

@Composable
fun NetworkStatusBanner(viewModel: NetworkViewModel) {
    val isConnected by viewModel.isConnected.collectAsState()

    if (!isConnected) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Green.copy(alpha = 0.7f))
                .clickable { },
            contentAlignment = Alignment.Center
        ) {
            NoInternetContent(
                onRetry = { viewModel.checkInitialConnection() }
            )
        }
    }
}