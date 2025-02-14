package com.keneth.products.data.Comments

data class CommentsResponse(
    val comments: List<Comment>,
    val limit: Int,
    val skip: Int,
    val total: Int
)