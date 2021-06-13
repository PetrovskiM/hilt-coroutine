package com.mpdevelopment.hiltcoroutine.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mpdevelopment.hiltcoroutine.BuildConfig
import com.mpdevelopment.hiltcoroutine.network.adapter.LocalDateTimeTypeAdapter
import com.mpdevelopment.hiltcoroutine.network.adapter.ZonedDateTimeTypeAdapter
import com.mpdevelopment.hiltcoroutine.network.interceptor.ErrorInterceptor
import com.mpdevelopment.hiltcoroutine.network.service.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.time.ZonedDateTime
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Named("baseUrl")
    fun provideApiBaseUrl(): String = BuildConfig.API_BASE_URL

    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder()
            .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeTypeAdapter())
            .registerTypeAdapter(ZonedDateTime::class.java, ZonedDateTimeTypeAdapter())
            .create()

    @Provides
    fun provideOkHttpClient(
        @Named("log") loggingInterceptor: Interceptor,
        @Named("error") errorInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(errorInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        httpClient: OkHttpClient,
        @Named("baseUrl") baseUrl: String,
        gson: Gson
    ): Retrofit =
        Retrofit.Builder()
            .client(httpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @Singleton
    @Named("log")
    fun provideLogInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    @Provides
    @Singleton
    @Named("error")
    fun provideErrorInterceptor(): Interceptor =
        ErrorInterceptor()

    @Provides
    @Singleton
    fun provideNewsService(retrofit: Retrofit): NewsService = retrofit.create(NewsService::class.java)

/* Error Subject mentioned in [ErrorInterceptor]
    @Provides
    @Singleton
    @ErrorStream
    fun provideApiErrorSubject(): PublishSubject<ApiError> = PublishSubject.create()

    @Provides
    @Singleton
    @ErrorStream
    fun provideApiErrorStream(@ErrorStream stream: PublishSubject<ApiError>): Observable<ApiError> =
        stream.hide()
*/

}