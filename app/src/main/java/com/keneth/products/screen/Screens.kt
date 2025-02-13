package com.keneth.products.screen

import androidx.annotation.DrawableRes
import com.keneth.products.R

sealed class Screens(val route: String, val title: String? = null, open val icon: Int? = null) {
    object SplashScreen : Screens("splash_screen")

    object ProductsScreen : Screens("products_screen")

    object ProductDetailsScreen : Screens("product_details_screen")
    object FruitsScreen : Screens("fruits_screen")
    object JuiceDetailsScreen : Screens("juice_details_screen")


    sealed class DrawerScreen(val dTitle: String, val dRoute: String, @DrawableRes override val icon: Int) :
        Screens(dTitle, dRoute) {
        object Account : DrawerScreen(
            "Account",
            "account",
            R.drawable.account
        )

        object Subscription : DrawerScreen(
            "Subscription",
            "subscribe",
            R.drawable.subscribe
        )

        object AddAccount : DrawerScreen(
            "Add Account",
            "add_account",
            R.drawable.add_account
        )
    }


    val screensInDrawer = listOf(
        Screens.DrawerScreen.Account,
        Screens.DrawerScreen.Subscription,
        Screens.DrawerScreen.AddAccount
    )

}