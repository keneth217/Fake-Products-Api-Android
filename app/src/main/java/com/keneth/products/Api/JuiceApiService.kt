package com.keneth.products.Api



import com.keneth.products.data.Juice.FruitsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Singleton Retrofit Instance
object JuiceRetrofitInstance {
    private const val BASE_URL = "https://dummyjson.com/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

   val juiceResults: JuiceApiService = retrofit.create(JuiceApiService::class.java);
}

// Define API Service Interface
interface JuiceApiService {
    @GET("products")
    suspend fun getProducts(): FruitsResponse


}
