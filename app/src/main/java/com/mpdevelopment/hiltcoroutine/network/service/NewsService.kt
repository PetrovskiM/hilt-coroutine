package com.mpdevelopment.hiltcoroutine.network.service

import com.mpdevelopment.hiltcoroutine.model.api.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("news")
    suspend fun getNews(@Query("id") id: String, @Query("rtag") rtag: String): NewsResponse
}