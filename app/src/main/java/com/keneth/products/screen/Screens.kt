package com.keneth.products.screen


sealed class Screens(val route: String, val title: String? = null, open val icon: Int? = null) {
    object SplashScreen : Screens("splash_screen",)

    object ProductsScreen : Screens("products_screen","Products")

    object ProductDetailsScreen : Screens("product_details_screen","Product Details")
    object FruitsScreen : Screens("fruits_screen","Fruits")
    object JuiceDetailsScreen : Screens("juice_details_screen","Juice Details")

    object QuotesScreen : Screens("quotes_screen","Quotes")
    object PostsScreen : Screens("posts_screen","Posts")
    object CommentsScreen : Screens("comments_screen","Comments")
    object QuotesDetailsScreen : Screens("quotes_details_screen","Quotes Details")
    object PostsDetailsScreen : Screens("posts_details_screen","Posts Details")
    object CommentsDetailsScreen : Screens("comments_details_screen","Comments Details")




}