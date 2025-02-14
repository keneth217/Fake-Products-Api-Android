package com.keneth.products.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keneth.products.Api.DummyJsonRetrofitInstance
import com.keneth.products.data.Comments.CommentsResponse
import com.keneth.products.data.Posts.PostsResponse
import kotlinx.coroutines.launch

class CommentsViewModel : ViewModel() {
    private val _comments = mutableStateOf(CommentsState())
    val comments: State<CommentsState> = _comments


    init {
        fetchComments()
    }

    fun fetchComments() {
        _comments.value = _comments.value.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val commentsResponse = DummyJsonRetrofitInstance.dummyJsonResults.getComments()
                if (commentsResponse.comments.isNotEmpty()) {
                    _comments.value = _comments.value.copy(
                        commentLists = commentsResponse,
                        isLoading = false,
                        error = null
                    )
                } else {
                    _comments.value = _comments.value.copy(
                        isLoading = false,
                        error = "No comments available"
                    )
                }
            } catch (e: Exception) {
                _comments.value = _comments.value.copy(
                    isLoading = false,
                    error = "An error occurred: ${e.localizedMessage}"
                )
            }
        }


    }


    data class CommentsState(
        val commentLists: CommentsResponse = CommentsResponse(emptyList(), 0, 0, 0),
        val isLoading: Boolean = false,
        val error: String? = null
    )


}