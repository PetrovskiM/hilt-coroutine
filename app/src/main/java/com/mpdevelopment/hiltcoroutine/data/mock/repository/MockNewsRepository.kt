package com.mpdevelopment.hiltcoroutine.data.mock.repository

import com.mpdevelopment.hiltcoroutine.model.Enclosure
import com.mpdevelopment.hiltcoroutine.model.NewsItem
import com.mpdevelopment.hiltcoroutine.model.api.NewsResponse
import com.mpdevelopment.hiltcoroutine.network.service.NewsService
import java.time.ZonedDateTime
import kotlin.random.Random

class MockNewsService : NewsService {

    override suspend fun getNews(id: String, rtag: String): NewsResponse =
        NewsResponse(getNewsItems())

    private fun getNewsItems(): List<NewsItem> = listOf(
        NewsItem(
            "First News Item Title", "First By Line", ZonedDateTime.now().minusDays(
                Random.nextLong(0, 8)
            ),
            "https://www.google.com",
            Random.nextDouble(0.5, 12.56).toString(),
            "First Author",
            "https://api.time.com/wp-content/uploads/2015/02/imsis270-064.jpg?w=941&quality=85",
            "https://api.time.com/wp-content/uploads/2015/02/imsis270-064.jpg?w=941&quality=85",
            "First short description",
            "First content",
            Enclosure("https://api.time.com/wp-content/uploads/2015/02/imsis270-064.jpg?w=941&quality=85", "First Format"),
            listOf("First category")
        ),
        NewsItem(
            "Second News Item Title", "Second By Line", ZonedDateTime.now().minusDays(
                Random.nextLong(0, 8)
            ),
            "https://www.google.com",
            Random.nextDouble(0.5, 12.56).toString(),
            "Second Author",
            "https://www.designingbuildings.co.uk/w/images/e/ec/Hexnut.jpg",
            "https://www.designingbuildings.co.uk/w/images/e/ec/Hexnut.jpg",
            "Second short description",
            "Second content",
            Enclosure("https://www.designingbuildings.co.uk/w/images/e/ec/Hexnut.jpg", "Second Format"),
            listOf("Second category")
        ),
        NewsItem(
            "Third News Item Title", "Third By Line", ZonedDateTime.now().minusDays(
                Random.nextLong(0, 8)
            ),
            "https://www.google.com",
            Random.nextDouble(0.5, 12.56).toString(),
            "Third Author",
            "https://www.bluediamond.com/assets/979d6d5d188af1d18350de5b31857320.png",
            "https://www.bluediamond.com/assets/979d6d5d188af1d18350de5b31857320.png",
            "Third short description",
            "Third content",
            Enclosure("https://www.bluediamond.com/assets/979d6d5d188af1d18350de5b31857320.png", "Third Format"),
            listOf("Third category")
        )
    )
}