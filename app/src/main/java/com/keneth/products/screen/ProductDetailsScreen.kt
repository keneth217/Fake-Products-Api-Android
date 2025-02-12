package com.keneth.products.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil3.compose.rememberAsyncImagePainter
import com.keneth.products.data.Products
import com.keneth.products.data.ProductsItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(
    product: ProductsItem,
    navController: NavHostController // Pass NavController from parent
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Product Details",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack() // Correct back navigation
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = product.title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge
            )

            Image(
                painter = rememberAsyncImagePainter(product.image),
                contentDescription = "${product.image} thumbnail",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp) // Set image height for better UI
                    .padding(vertical = 8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Price: \$${product.price}", style = MaterialTheme.typography.bodyLarge)
//                VerticalDivider(
//                    color = MaterialTheme.colorScheme.primary,
//                    thickness = 1.dp,
//                    modifier = Modifier.padding(horizontal = 8.dp)
//                )
                Text(
                    text = "⭐ ${product.rating.rate} (${product.rating.count})",
                    style = MaterialTheme.typography.bodyLarge

                )
            }

            Text(
                text = product.description + "Hi Devs, in this article i am going to show you how you can design bottom navigation bar in jetpack compose using navigation component(nav controller and nav host).\n" +
                        "\n" +
                        "For this article I have designed bottom navigation bar just like LinkedIn. In jetpack compose you don’t need to define your navigation under navigation directory and create its navigation graph. Navigation in jetpack compose is different from xml navigation where we need to define the fragments and graph. Add below dependency in your app level gradle file and rebuild your app.\n" +
                        "\n" +
                        "implementation \"androidx.navigation:navigation-compose:2.4.0-beta02\"\n" +
                        "Hi Devs, in this article i am going to show you how you can design bottom navigation bar in jetpack compose using navigation component(nav controller and nav host).\n" +
                        "\n" +
                        "For this article I have designed bottom navigation bar just like LinkedIn. In jetpack compose you don’t need to define your navigation under navigation directory and create its navigation graph. Navigation in jetpack compose is different from xml navigation where we need to define the fragments and graph. Add below dependency in your app level gradle file and rebuild your app.\n" +
                        "\n" +
                        "implementation \"androidx.navigation:navigation-compose:2.4.0-beta02\"\n" +
                        "Hi Devs, in this article i am going to show you how you can design bottom navigation bar in jetpack compose using navigation component(nav controller and nav host).\n" +
                        "\n" +
                        "For this article I have designed bottom navigation bar just like LinkedIn. In jetpack compose you don’t need to define your navigation under navigation directory and create its navigation graph. Navigation in jetpack compose is different from xml navigation where we need to define the fragments and graph. Add below dependency in your app level gradle file and rebuild your app.\n" +
                        "\n" +
                        "implementation \"androidx.navigation:navigation-compose:2.4.0-beta02\"\n" +
                        "Hi Devs, in this article i am going to show you how you can design bottom navigation bar in jetpack compose using navigation component(nav controller and nav host).\n" +
                        "\n" +
                        "For this article I have designed bottom navigation bar just like LinkedIn. In jetpack compose you don’t need to define your navigation under navigation directory and create its navigation graph. Navigation in jetpack compose is different from xml navigation where we need to define the fragments and graph. Add below dependency in your app level gradle file and rebuild your app.\n" +
                        "\n" +
                        "implementation \"androidx.navigation:navigation-compose:2.4.0-beta02\"\n" +
                        "Hi Devs, in this article i am going to show you how you can design bottom navigation bar in jetpack compose using navigation component(nav controller and nav host).\n" +
                        "\n" +
                        "For this article I have designed bottom navigation bar just like LinkedIn. In jetpack compose you don’t need to define your navigation under navigation directory and create its navigation graph. Navigation in jetpack compose is different from xml navigation where we need to define the fragments and graph. Add below dependency in your app level gradle file and rebuild your app.\n" +
                        "\n" +
                        "implementation \"androidx.navigation:navigation-compose:2.4.0-beta02\"\n" +
                        "After rebuilding your app create a separate directory for bottom navigation related files. Now create sealed class with name BottomNavItem with bottom navigation item title, item icon and item route which we will use later for navigation between screens just like below:",
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.verticalScroll(rememberScrollState()).padding(top=20.dp)
            )
        }
    }
}


