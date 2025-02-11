package com.keneth.products.screen
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.keneth.products.model.ProductsViewModel
import com.keneth.products.data.ProductsItem
import kotlinx.coroutines.launch





@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductsScreen(
    modifier: Modifier = Modifier,
    viewState: ProductsViewModel.ProductsState,
    navigateToProductsDetails: (ProductsItem) -> Unit
) {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(12.dp))
                    // Add your drawer content here
                    Text("Categories", modifier = Modifier.padding(16.dp))
                    Divider()
                    NavigationDrawerItem(
                        label = { Text("Electronics") },
                        selected = false,
                        onClick = { /* Handle category selection */ }
                    )
                    NavigationDrawerItem(
                        label = { Text("Jewelry") },
                        selected = false,
                        onClick = { /* Handle category selection */ }
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "Products",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) drawerState.open() else drawerState.close()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
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
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                when {
                    viewState.isLoading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                    viewState.error != null -> {
                        Text(
                            text = viewState.error,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    else -> {
                        ProductList(
                            products = viewState.productsList,
                            navigateToProductsDetails = navigateToProductsDetails
                        )
                    }
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
                modifier = Modifier
                    .padding(8.dp),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(product.image),
                    contentDescription = product.title,
                    modifier = Modifier
                        .size(128.dp)
                        .padding(8.dp)
                )

                Text(
                    text = product.title,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(4.dp)
                )

                Row(modifier = Modifier.padding(top = 8.dp)) {
                    Text(
                        text = "$${product.price}",
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                    Text(
                        text = "⭐ ${product.rating.rate} (${product.rating.count})",
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
            }
        }
    }
}