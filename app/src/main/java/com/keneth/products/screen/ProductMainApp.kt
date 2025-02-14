package com.keneth.products.screen

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.keneth.products.data.Clothes.ProductsItem
import com.keneth.products.data.Clothes.Rating
import com.keneth.products.data.Comments.Comment
import com.keneth.products.data.Comments.User
import com.keneth.products.data.Juice.Dimensions
import com.keneth.products.data.Juice.JuiceProducts
import com.keneth.products.data.Juice.Meta
import com.keneth.products.data.Posts.Post
import com.keneth.products.data.Posts.Reactions
import com.keneth.products.data.Quotes.Quote
import com.keneth.products.model.CommentsViewModel
import com.keneth.products.model.JuiceViewModel
import com.keneth.products.model.PostsViewModel
import com.keneth.products.model.ProductsViewModel
import com.keneth.products.model.QuotesViewModel

@Composable
fun ProductMainApp() {
    val navController = rememberNavController()
    val productsViewModel: ProductsViewModel = viewModel()
    val viewState by productsViewModel.productsState
    val juiceViewModel: JuiceViewModel = viewModel()
    val fruitViewState by juiceViewModel.juiceState

    val quotesViewModel: QuotesViewModel = viewModel()
    val quotesViewState by quotesViewModel.quotes

    val commentsViewModel: CommentsViewModel = viewModel()
    val commentsViewState by commentsViewModel.comments

    val postsViewModel: PostsViewModel = viewModel()
    val postsViewState by postsViewModel.posts

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
                modifier = Modifier, navController = navController
            )
        }
        composable(route = Screens.ProductDetailsScreen.route) {
            val product =
                navController.previousBackStackEntry?.savedStateHandle?.get<ProductsItem>("products")
                    ?: ProductsItem("", "", 0, "", 0.0, Rating(0, 0.0), "")
            ProductDetailsScreen(product, navController)
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
            val juice =
                navController.previousBackStackEntry?.savedStateHandle?.get<JuiceProducts>("juice")
                    ?: JuiceProducts(
                        availabilityStatus = "",
                        brand = "",
                        category = "",
                        description = "",
                        dimensions = Dimensions(0.0, 0.0, 0.0), // Assuming this structure
                        discountPercentage = 0.0,
                        id = 0,
                        images = emptyList(),
                        meta = Meta("", "", "", ""), // Assuming this structure
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
        composable(route = Screens.QuotesScreen.route) {
            QuotesScreen(
                viewState = quotesViewState,
                navigateToQuotesDetails = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("quotes", it)
                    navController.navigate(Screens.QuotesDetailsScreen.route)
                },
                modifier = Modifier,
                navController = navController
            )

        }
        composable(route = Screens.QuotesDetailsScreen.route) {
            val quotes =
                navController.previousBackStackEntry?.savedStateHandle?.get<Quote>("quotes")
                    ?: Quote("", 0, "")
            QuotesDetailsScreen(quotes, navController)
        }
        composable(route = Screens.PostsScreen.route) {
            PostsScreen(
                viewState = postsViewState,
                navigateToPostsDetails = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("posts", it)
                    navController.navigate(Screens.PostsDetailsScreen.route)
                },
                modifier = Modifier, navController = navController
            )

        }
        composable(route = Screens.PostsDetailsScreen.route){
            val posts =
                navController.previousBackStackEntry?.savedStateHandle?.get<Post>("posts")
                    ?:  Post("", 1, reactions = Reactions(0, 0), tags = emptyList(), "", 0, 0)
            PostsDetailsScreen(posts, navController)
        }
        composable(route = Screens.CommentsScreen.route) {
            CommentsScreen(
                viewState = commentsViewState,
                navigateToCommentsDetails = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("comments", it)
                    navController.navigate(Screens.CommentsDetailsScreen.route)
                }, modifier = Modifier, navController = navController
            )
        }
        composable(route = Screens.CommentsDetailsScreen.route) {
            val comments =
                navController.previousBackStackEntry?.savedStateHandle?.get<Comment>("comments")
                    ?: Comment("", 0, 0, 0, user = User("", 1,  ""))
        }
    }
}