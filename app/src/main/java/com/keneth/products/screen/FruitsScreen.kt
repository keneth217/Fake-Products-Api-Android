package com.keneth.products.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.keneth.products.data.Juice.FruitsResponse
import com.keneth.products.data.Juice.JuiceProducts
import com.keneth.products.model.JuiceViewModel


@Composable
fun FruitsScreen(
    modifier: Modifier = Modifier,
    viewState: JuiceViewModel.JuiceState,
    navigateToJuiceDetails: (JuiceProducts) -> Unit
) {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntry?.destination?.route ?: "home"
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { ModalDrawerSheet { AppDrawer(drawerState, scope) } }
    ) {
        Scaffold(
            topBar = {
                TopBar(scope, drawerState)
            },
            bottomBar = { BottomNavBar(navController, currentRoute) }
        ) { innerPadding ->
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                when {
                    viewState.isLoading -> CircularProgressIndicator(
                        modifier = Modifier.align(
                            Alignment.Center
                        )
                    )

                    viewState.error != null -> Text(
                        text = viewState.error,
                        modifier = Modifier.align(Alignment.Center)
                    )

                    else -> FruitsList(
                        juice = viewState.juiceList,
                        navigateToProductsDetails = navigateToJuiceDetails
                    )
                }
            }
        }
    }
}


@Composable
fun FruitsList(
    juice: FruitsResponse,
    navigateToProductsDetails: (JuiceProducts) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        items(juice.products) { juice ->
            FruitItem(
                juice = juice,
                navigateToProductsDetails = navigateToProductsDetails
            )
        }
    }
}

@Composable
fun FruitItem(
    juice: JuiceProducts,
    navigateToProductsDetails: (JuiceProducts) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigateToProductsDetails(juice) }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(

                        ImageRequest.Builder(LocalContext.current)
                            .data(juice.thumbnail ?: "https://cdn.dummyjson.com/products/images/groceries/Juice/1.png")
                            .crossfade(true)
                            .build()
                    ),

                    contentDescription = juice.title,
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                    alpha = 1f,
                    colorFilter = null,

                    modifier = Modifier
                        .size(128.dp)
                        .padding(8.dp)
                )
                Text(
                    text = juice.title,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(4.dp)
                )
                Row(modifier = Modifier.padding(top = 8.dp)) {
                    Text(text = "$${juice.price}", modifier = Modifier.padding(horizontal = 4.dp))
                    Text(
                        text = "⭐ ${juice.rating} (${juice.brand})",
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
            }
        }
    }
}

