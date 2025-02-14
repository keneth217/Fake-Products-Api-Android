package com.keneth.products.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.keneth.products.data.Quotes.Quote
import com.keneth.products.data.Quotes.QuotesResponse
import com.keneth.products.model.QuotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuotesScreen(
    modifier: Modifier = Modifier,  // Default empty Modifier for flexibility
    navigateToQuotesDetails: (Quote) -> Unit,
    viewState: QuotesViewModel.QuotesStates,
    navController: NavHostController
) {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val currentRoute =
        navController.currentBackStackEntry?.destination?.route ?: Screens.ProductsScreen.route


    // Create Bottom Sheet State
    val bottomSheetState = rememberModalBottomSheetState()
    var isBottomSheetOpen by remember { mutableStateOf(false) }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                AppDrawer(
                    drawerState,
                    scope,
                    onItemClick = { route -> navController.navigate(route) })
            }
        }
    ) {
        Scaffold(
            topBar = { TopBar(scope, drawerState, "Quotes", onBottomSheetOpen = {}) },
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
                        modifier = Modifier.align(Alignment.Center)
                    )

                    viewState.error != null -> Text(
                        text = viewState.error,
                        modifier = Modifier.align(Alignment.Center)
                    )

                    else -> QuoteList(
                        quotes = viewState.quotesList,
                        navigateToQuotesDetails = navigateToQuotesDetails
                    )
                }
            }
        }
    }
    // **Bottom Sheet Implementation**
    if (isBottomSheetOpen) {
        ModalBottomSheet(
            onDismissRequest = { isBottomSheetOpen = false },
            sheetState = bottomSheetState
        ) {
            BottomSheetContent { isBottomSheetOpen = false }
        }
    }

}

@Composable
fun QuoteList(
    quotes: QuotesResponse,
    navigateToQuotesDetails: (Quote) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)  // Adds spacing between items
    ) {
        items(quotes.quotes) { quote ->  // Fixed naming
            QuoteItem(
                quote = quote,
                navigateToQuotesDetails = navigateToQuotesDetails
            )
        }
    }
}

@Composable
fun QuoteItem(
    quote: Quote,
    navigateToQuotesDetails: (Quote) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigateToQuotesDetails(quote) }
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)  // Adds shadow for better UI
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = quote.author,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = quote.quote,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


@Composable
fun BottomSheetContent(onClose: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is the Bottom Sheet", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onClose) {
            Text("Close")
        }
    }
}
