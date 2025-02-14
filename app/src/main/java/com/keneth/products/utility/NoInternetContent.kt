package com.keneth.products.utility

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun NoInternetContent(onRetry: () -> Unit) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_no_internet),
            contentDescription = "No internet",
            tint = Color.White,
            modifier = Modifier.size(64.dp)

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                    text = "No Internet Connection",
            color = Color.White,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(8.dp))



        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ) {
            Text("Try Again")
        }
    }
}