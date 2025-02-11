package com.keneth.products.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.keneth.products.R

import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    val DarkPink = Color(0xFFe496ee)
    LaunchedEffect(Unit) {
        delay(3000) // Wait for 3 seconds
        navController.navigate(Screens.ProductsScreen.route) {
            popUpTo(Screens.SplashScreen.route) { inclusive = true }
        }

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkPink),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background), // Replace with your logo
            contentDescription = "Splash Logo",
            modifier = Modifier.size(150.dp)
        )
        Text(
            "WELCOME TO ${stringResource(id = R.string.app_name)}",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White
        )

    }

}