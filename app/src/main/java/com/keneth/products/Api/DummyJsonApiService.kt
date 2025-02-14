package com.keneth.products.Api



import com.keneth.products.data.Comments.CommentsResponse
import com.keneth.products.data.Juice.FruitsResponse
import com.keneth.products.data.Posts.PostsResponse
import com.keneth.products.data.Quotes.QuotesResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Singleton Retrofit Instance
object DummyJsonRetrofitInstance {
    private const val BASE_URL = "https://dummyjson.com/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

   val dummyJsonResults: DummyJsonApiService = retrofit.create(DummyJsonApiService::class.java);
}

// Define API Service Interface
interface DummyJsonApiService {
    @GET("products")
    suspend fun getProducts(): FruitsResponse

    @GET("quotes")
    suspend fun getQuotes(): QuotesResponse

    @GET("posts")
    suspend fun getPosts(): PostsResponse

    @GET("comments")
    suspend fun  getComments(): CommentsResponse




}
