package com.mpdevelopment.hiltcoroutine.data.cache

import com.mpdevelopment.hiltcoroutine.data.common.DataItem
import com.mpdevelopment.hiltcoroutine.data.common.DataSource
import java.util.concurrent.ConcurrentHashMap

class InMemoryCacheDataSource<T : DataItem<Id>, Id> : DataSource<T> {

    private val map = ConcurrentHashMap<Id, T>()

    override suspend fun get(): List<T> = map.values.toList()

    override suspend fun save(data: List<T>) {
        data.forEach { map[it.getId()] = it }
    }
}