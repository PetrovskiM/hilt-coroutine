package com.mpdevelopment.hiltcoroutine.data.repository

import com.mpdevelopment.hiltcoroutine.data.common.DataSource
import com.mpdevelopment.hiltcoroutine.model.NewsItem
import com.mpdevelopment.hiltcoroutine.network.service.NewsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

//TODO we can also add a Room DB for offline support, it will be another DataSource in the get method
//We can also add a data specification which will indicate when the cache is outdated, when to get from api etc.
class NewsRepository @Inject constructor(
    private val newsService: NewsService,
    private val cache: DataSource<NewsItem>
) : DataSource<NewsItem> {

    override suspend fun get(): List<NewsItem> = withContext(Dispatchers.IO) {
        val cacheItems = cache.get()
        return@withContext if (cacheItems.isNotEmpty()) {
            cacheItems
        } else {
            val news = newsService.getNews("acmilan", "acmilan").items
            cache.save(news)
            news
        }
    }


    override suspend fun save(data: List<NewsItem>) = cache.save(data)

}