package com.keneth.products

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Singleton Retrofit Instance
object RetrofitInstance {
    private const val BASE_URL = "https://fakestoreapi.com/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

// Define API Service Interface
interface ApiService {
    @GET("products")
    suspend fun getProducts(): Response<List<ProductsItem>>
}
