package com.keneth.products.data.Comments

data class Comment(
    val body: String,
    val id: Int,
    val likes: Int,
    val postId: Int,
    val user: User
)