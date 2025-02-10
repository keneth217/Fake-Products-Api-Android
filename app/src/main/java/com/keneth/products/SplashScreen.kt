package com.keneth.products


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(3000) // Wait for 3 seconds
        navController.navigate("main") {
            popUpTo("splash") { inclusive = true }
        }

    }
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background), // Replace with your logo
                contentDescription = "Splash Logo",
                modifier = Modifier.size(150.dp)
            )
            Text("WELCOME TO $ {stringResource(id = R.string.app_name)}")
        }

}