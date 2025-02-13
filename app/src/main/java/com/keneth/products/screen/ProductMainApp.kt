package com.keneth.products.screen

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.keneth.products.data.Clothes.ProductsItem
import com.keneth.products.data.Clothes.Rating
import com.keneth.products.data.Juice.Dimensions
import com.keneth.products.data.Juice.JuiceProducts
import com.keneth.products.data.Juice.Meta
import com.keneth.products.model.JuiceViewModel
import com.keneth.products.model.ProductsViewModel

@Composable
fun ProductMainApp() {
    val navController = rememberNavController()
    val productsViewModel: ProductsViewModel = viewModel()
    val viewState by productsViewModel.productsState

    val juiceViewModel: JuiceViewModel = viewModel()
    val fruitViewState by juiceViewModel.juiceState

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

        composable(route = Screens.FruitsScreen.route) {
            FruitsScreen(
                viewState = fruitViewState,
                navigateToJuiceDetails = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("juice", it)
                    navController.navigate(Screens.JuiceDetailsScreen.route)
                },
                modifier = Modifier
            )
        }

        composable(route = Screens.JuiceDetailsScreen.route) {
            val juice = navController.previousBackStackEntry?.savedStateHandle?.get<JuiceProducts>("juice")
                ?: JuiceProducts(
                    availabilityStatus = "",
                    brand = "",
                    category = "",
                    description = "",
                    dimensions = Dimensions(0.0, 0.0, 0.0), // Assuming this structure
                    discountPercentage = 0.0,
                    id = 0,
                    images = emptyList(),
                    meta = Meta("", "","",""), // Assuming this structure
                    minimumOrderQuantity = 0,
                    price = 0.0,
                    rating = 0.0,
                    returnPolicy = "",
                    reviews = emptyList(),
                    shippingInformation = "",
                    sku = "",
                    stock = 0,
                    tags = emptyList(),
                    thumbnail = "",
                    title = "",
                    warrantyInformation = "",
                    weight = 0
                )

            JuiceDetailsScreen(juice, navController)
        }

    }
}
