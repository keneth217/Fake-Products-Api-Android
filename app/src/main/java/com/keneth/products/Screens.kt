package com.keneth.products

sealed class Screens(val route: String) {
    object SplashScreen : Screens("splash_screen")

    object ProductsScreen : Screens("products_screen")

    object ProductDetailsScreen : Screens("product_details_screen")

}