package com.keneth.products.screen

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.keneth.products.data.ProductsItem
import com.keneth.products.data.Rating
import com.keneth.products.model.ProductsViewModel

@Composable
fun ProductMainApp() {
    val navController = rememberNavController()
    val productsViewModel: ProductsViewModel = viewModel()
    val viewState by productsViewModel.productsState

    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {
        composable(route = Screens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(route = Screens.ProductsScreen.route) {
            ProductsScreen(
                viewState = viewState,
                navigateToProductsDetails = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("products", it)
                    navController.navigate(Screens.ProductDetailsScreen.route)
                },
                modifier = Modifier
            )
        }
        composable(route = Screens.ProductDetailsScreen.route) {
            val product = navController.previousBackStackEntry?.savedStateHandle?.get<ProductsItem>("products")
                ?: ProductsItem("", "", 0, "", 0.0, Rating(0, 0.0), "")
            ProductDetailsScreen(product,navController)
        }
    }
}
