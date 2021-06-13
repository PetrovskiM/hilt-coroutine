package com.mpdevelopment.hiltcoroutine.feature.home

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RefreshStreamModule {

    @Provides
    @Singleton
    @Named("refreshStream")
    fun provideRefreshStream(): MutableSharedFlow<Boolean> = MutableSharedFlow()

    @Provides
    @Singleton
    @Named("refreshFlow")
    fun provideRefreshStreamFlow(@Named("refreshStream") mutableFlow: MutableSharedFlow<Boolean>): Flow<Boolean> =
        mutableFlow.asSharedFlow()
}