package com.keneth.products.utility

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material3.Icon
import com.keneth.products.R

@Composable
fun NoInternetContent(onRetry: () -> Unit) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.no_internet),
            contentDescription = "No internet",
            tint = Color.Red,
            modifier = Modifier.size(64.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "No Internet Connection",
            color = Color.White,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()  // Fill the available width instead of full screen
                .padding(horizontal = 16.dp, vertical = 8.dp), // Add spacing
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Magenta,  // Background color
                contentColor = Color.White  // Text color
            ),
            shape = MaterialTheme.shapes.medium,  // Rounded corners
            elevation = ButtonDefaults.elevatedButtonElevation(8.dp) // Adds a shadow effect
        ) {
            Text(
                text = "Retry Connection",
                fontSize = 18.sp,  // Slightly larger text
                fontWeight = FontWeight.Bold
            )
        }

    }
}
