package com.keneth.products

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.rememberAsyncImagePainter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductsScreen(){
    val viewModel: ProductsViewModel = viewModel()
    val products = viewModel.productsState
    Scaffold() {

        LazyVerticalGrid(

            columns = GridCells.Adaptive(minSize = 128.dp)
        ) {
            items(products.value.productsList.size) { index ->
                ProductItem(product = products.value.productsList[index])
            }
        }

    }

}

@Composable
fun ProductItem(product: ProductsItem) {

    Card(){
        Row {

            Image(
                painter = rememberAsyncImagePainter(product.image),
                contentDescription = null,
                //modifier = Modifier.size(128.dp)
            )
            Text(text = product.title, modifier = Modifier.padding(16.dp))
            Text(text = product.description, modifier = Modifier.padding(16.dp))

            Row {

                Text(text = product.price.toString(), modifier = Modifier.padding(16.dp))
                Text(text = product.rating.rate.toString(), modifier = Modifier.padding(16.dp))
                Text(text = product.rating.count.toString(), modifier = Modifier.padding(16.dp))
            }

        }


    }

}
@Preview(showBackground = true)
@Composable
fun  ProductsItemPreview(){
    ProductsItem("","",0,"",0.0,Rating(0,0.0),"")
}


