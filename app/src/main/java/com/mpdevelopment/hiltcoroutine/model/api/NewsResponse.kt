package com.mpdevelopment.hiltcoroutine.model.api

import com.google.gson.annotations.SerializedName
import com.mpdevelopment.hiltcoroutine.model.NewsItem

data class NewsResponse(@SerializedName("items") val items: List<NewsItem>)
