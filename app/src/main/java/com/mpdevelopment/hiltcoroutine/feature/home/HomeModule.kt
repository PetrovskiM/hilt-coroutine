package com.mpdevelopment.hiltcoroutine.feature.home

import com.mpdevelopment.hiltcoroutine.BuildConfig
import com.mpdevelopment.hiltcoroutine.data.cache.InMemoryCacheDataSource
import com.mpdevelopment.hiltcoroutine.data.common.DataSource
import com.mpdevelopment.hiltcoroutine.data.mock.repository.MockNewsService
import com.mpdevelopment.hiltcoroutine.data.repository.NewsRepository
import com.mpdevelopment.hiltcoroutine.model.NewsItem
import com.mpdevelopment.hiltcoroutine.network.service.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
object HomeModule {

    @Provides
    fun provideNewsRepository(
        newsService: NewsService,
        @Named("cache") cache: DataSource<NewsItem>
    ): DataSource<NewsItem> = if (BuildConfig.DEBUG) {
        NewsRepository(MockNewsService(), cache)
    } else {
        NewsRepository(newsService, cache)
    }

    @Provides
    @Named("cache")
    fun provideCache(): DataSource<NewsItem> = InMemoryCacheDataSource()
}