package com.keneth.products.screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
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
import com.keneth.products.model.ProductsViewModel
import com.keneth.products.data.Clothes.ProductsItem


@Composable
fun ProductsScreen(
    modifier: Modifier = Modifier,
    viewState: ProductsViewModel.ProductsState,
    navigateToProductsDetails: (ProductsItem) -> Unit
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

                    else -> ProductList(
                        products = viewState.productsList,
                        navigateToProductsDetails = navigateToProductsDetails
                    )
                }
            }
        }
    }
}












@Composable
fun ProductList(
    products: List<ProductsItem>,
    navigateToProductsDetails: (ProductsItem) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        items(products) { product ->
            ProductItem(
                product = product,
                navigateToProductsDetails = navigateToProductsDetails
            )
        }
    }
}

@Composable
fun ProductItem(
    product: ProductsItem,
    navigateToProductsDetails: (ProductsItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigateToProductsDetails(product) }
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
                            .data(product.image)
                            .crossfade(true)
                            .build()
                    ),
                    contentDescription = product.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(128.dp)
                        .padding(8.dp)
                )
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.bodyLarge, maxLines = 2,
                    modifier = Modifier.padding(4.dp)
                )
                Row(modifier = Modifier.padding(top = 8.dp)) {
                    Text(text = "$${product.price}", modifier = Modifier.padding(horizontal = 4.dp))
                    Text(
                        text = "⭐ ${product.rating.rate} (${product.rating.count})",
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
            }
        }
    }
}
