package com.keneth.products

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun ProductMainApp(

    navHostController: NavHostController,

    modifier: Modifier = Modifier
        .fillMaxSize()
        .padding(top = 16.dp)
) {
    val productsViewModel: ProductsViewModel = viewModel()
    val products = productsViewModel.productsState

    NavHost(
        navController = navHostController,
        startDestination = Screens.SplashScreen.route,
        modifier = modifier
    ) {
        composable(route = Screens.SplashScreen.route) {
            SplashScreen(navController = navHostController)
        }
        composable(route = Screens.ProductsScreen.route) {
            ProductsScreen(navController = navHostController)

        }
    }

}