package com.keneth.products.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.keneth.products.data.Juice.JuiceProducts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.keneth.products.data.Juice.Dimensions
import com.keneth.products.data.Juice.Meta
import com.keneth.products.data.Juice.Review


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JuiceDetailsScreen(
    juice: JuiceProducts,
    navController: NavHostController
) {
    val tabs = listOf("Description", "Reviews", "Return Policy", "Warranty", "Dimensions", "Meta")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

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
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
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
                text = juice.title,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )

            LazyHorizontalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(270.dp)
                    .padding(8.dp),
                rows = GridCells.Fixed(1),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                items(juice.images) { imageUrl ->
                    Card(
                        modifier = Modifier
                            .width(300.dp)
                            .height(250.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data(
                                        imageUrl
                                            ?: "https://cdn.dummyjson.com/products/images/groceries/Juice/1.png"
                                    )
                                    .crossfade(true)
                                    .build()
                            ),
                            contentDescription = "Product image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("category : ${juice.category}:", Modifier.padding(end = 8.dp)

                )
                Text("brand :${juice.brand}")

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Price: ${juice.price}", Modifier.padding(end = 8.dp))
                Text("Rating: ${juice.rating}", Modifier.padding(end = 8.dp))
                Text("Stock: ${juice.stock}", Modifier.padding(end = 8.dp))
            }

            // Tabs Row
            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                edgePadding = 0.dp,
                // containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary,
//                divider = {
//                    TabRowDefaults.divider(
//                        color = MaterialTheme.colorScheme.outline
//                    )
//                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(text = title) }
                    )
                }
            }

            // Tab Content
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(vertical = 8.dp)
            ) {
                when (tabs[selectedTabIndex]) {
                    "Description" -> DescriptionTab(juice.description)
                    "Reviews" -> ReviewsTab(juice.reviews)
                    "Return Policy" -> PolicyTab(juice.returnPolicy)
                    "Warranty" -> WarrantyTab(juice.warrantyInformation)
                    "Dimensions" -> DimensionsTab(juice.dimensions)
                    "Meta" -> MetaTab(juice.meta)
                }
            }
        }
    }
}

@Composable
fun DescriptionTab(description: String) {
    Text(
        text = description,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Justify,
        modifier = Modifier.verticalScroll(rememberScrollState())
    )
}

@Composable
fun ReviewsTab(reviews: List<Review>) {
    LazyColumn {
        items(reviews) { review ->
            ReviewItem(review)
        }
    }
}

@Composable
fun ReviewItem(review: Review) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = review.reviewerName, style = MaterialTheme.typography.labelLarge)
            Text(text = "Rating: ${review.rating}/5")
            Text(text = review.comment)
            Text(text = review.date)
            Text(text = review.reviewerEmail)


        }
    }
}

@Composable
fun PolicyTab(policy: String) {
    Text(
        text = policy,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun WarrantyTab(warranty: String) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Warranty Details:", style = MaterialTheme.typography.titleMedium)
        Text(text = warranty, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun DimensionsTab(dimensions: Dimensions) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Dimensions:", style = MaterialTheme.typography.titleMedium)
        Text(text = "Height: ${dimensions.height} cm")
        Text(text = "Width: ${dimensions.width} cm")
        Text(text = "Depth: ${dimensions.depth} cm")
        //  Text(text = "Weight: ${dimensions.weight} kg")
    }
}

@Composable
fun MetaTab(meta: Meta) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Product Metadata:", style = MaterialTheme.typography.titleMedium)
        Text(text = "Category: ${meta.qrCode}")
        Text(text = "Category: ${meta.barcode}")
        Text(text = "Manufacturer: ${meta.createdAt}")
        Text(text = "Manufacturer: ${meta.updatedAt}")
    }
}

