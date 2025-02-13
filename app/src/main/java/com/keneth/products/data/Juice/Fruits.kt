package com.keneth.products.data.Juice

data class FruitsResponse(
    val limit: Int,
    val products: List<JuiceProducts>,
    val skip: Int,
    val total: Int
)