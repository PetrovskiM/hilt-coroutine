package com.mpdevelopment.hiltcoroutine.data.common

interface DataItem<out Id> {
    fun getId(): Id
}