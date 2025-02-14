package com.keneth.products.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.keneth.products.data.Comments.Comment
import com.keneth.products.data.Comments.CommentsResponse
import com.keneth.products.data.Juice.FruitsResponse
import com.keneth.products.data.Posts.Post
import com.keneth.products.model.CommentsViewModel

@Composable
fun CommentsScreen(
    modifier: Modifier,
    viewState: CommentsViewModel.CommentsState,
    navigateToCommentsDetails: (Comment) -> Unit,
    navController: NavHostController
) {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val currentRoute = navController.currentBackStackEntry?.destination?.route ?: Screens.ProductsScreen.route
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { ModalDrawerSheet { AppDrawer(drawerState, scope,
            onItemClick = { route -> navController.navigate(route) }) } }
    ) {
        Scaffold(
            topBar = {
                TopBar(scope, drawerState, "Comments",onBottomSheetOpen = { /* Handle bottom sheet open */ })
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

                    else -> CommentList (
                        comments = viewState.commentLists,
                        navigateToCommentsDetails = navigateToCommentsDetails
                    )
                }
            }
        }
    }
}@Composable
fun CommentList(
    comments: CommentsResponse,
    navigateToCommentsDetails: (Comment) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(comments.comments) { comments ->
            CommentItem (
                comment = comments,
                navigateToCommentsDetails = navigateToCommentsDetails
            )
        }
    }
}

@Composable
fun CommentItem(
    comment: Comment,
    navigateToCommentsDetails: (Comment) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigateToCommentsDetails(comment) }
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

                Text(
                    text = comment.body,
                    style = MaterialTheme.typography.bodyLarge, maxLines = 2,
                    modifier = Modifier.padding(4.dp)
                )




                Text(
                    text = comment.user.username,

                    style = MaterialTheme.typography.bodyLarge, maxLines = 2,
                    modifier = Modifier.padding(4.dp)
                )

            }
        }
    }
}
