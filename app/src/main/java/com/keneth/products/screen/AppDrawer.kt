package com.keneth.products.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AppDrawer(drawerState: DrawerState, scope: CoroutineScope) {
    val categories = listOf("Electronics", "Jewelry", "Clothing", "Home & Garden")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        item {
            Spacer(Modifier.height(12.dp))
            Text(
                "Categories",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
            HorizontalDivider()
        }
        items(categories) { category ->
            NavigationDrawerItem(
                label = { Text(category) },
                selected = false,
                onClick = {
                    scope.launch { drawerState.close() }
                }
            )
        }
    }
}