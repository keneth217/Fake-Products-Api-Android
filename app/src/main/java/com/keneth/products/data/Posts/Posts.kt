package com.keneth.products.data.Posts

data class PostsResponse(
    val limit: Int,
    val posts: List<Post>,
    val skip: Int,
    val total: Int
)