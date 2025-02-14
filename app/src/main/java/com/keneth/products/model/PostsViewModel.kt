package com.keneth.products.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keneth.products.Api.DummyJsonRetrofitInstance
import com.keneth.products.data.Posts.PostsResponse
import kotlinx.coroutines.launch

class PostsViewModel : ViewModel() {
    private val _posts = mutableStateOf(PostsState())
    val posts: State<PostsState> = _posts

    init {
        fetchPosts()
    }

    fun fetchPosts() {
        _posts.value = _posts.value.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val postsResponse = DummyJsonRetrofitInstance.dummyJsonResults.getPosts()
                if (postsResponse.posts.isNotEmpty()) {
                    _posts.value = _posts.value.copy(
                        postsLists = postsResponse,
                        isLoading = false,
                        error = null
                    )
                } else {

                    _posts.value = _posts.value.copy(
                        isLoading = false,
                        error = "No posts available"
                    )
                }
            } catch (e: Exception) {

                _posts.value = _posts.value.copy(
                    isLoading = false,
                    error = "An error occurred: ${e.localizedMessage}"
                )
            }


        }


    }

    data class PostsState(
        val postsLists: PostsResponse = PostsResponse(0, emptyList(), 0, 0),
        val isLoading: Boolean = false,
        val error: String? = null
    )


}