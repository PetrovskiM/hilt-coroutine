package com.mpdevelopment.hiltcoroutine.data.common

interface DataSource<T> {
    suspend fun get(): List<T>
    suspend fun save(data: List<T>)
}