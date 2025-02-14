package com.keneth.products.data.Quotes

data class QuotesResponse(
    val limit: Int,
    val quotes: List<Quote>,
    val skip: Int,
    val total: Int
)