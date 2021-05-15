package com.pramod.newsapp.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY = "ccd287ed8fe54361a220b39b8e52d342"

interface NewsApiService {

    @GET("top-headlines?apiKey=$API_KEY")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("category") category: String
    ): ArticleResponse

}