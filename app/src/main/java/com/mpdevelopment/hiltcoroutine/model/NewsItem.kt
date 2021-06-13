package com.mpdevelopment.hiltcoroutine.model

import android.text.format.DateUtils
import com.google.gson.annotations.SerializedName
import com.mpdevelopment.hiltcoroutine.data.common.DataItem
import com.mpdevelopment.hiltcoroutine.extension.getMillis
import java.time.LocalDateTime
import java.time.ZonedDateTime

data class NewsItem(
    @SerializedName("title") val title: String,
    @SerializedName("byline") val byLine: String,
    @SerializedName("date_published") val datePublished: ZonedDateTime,
    @SerializedName("permalink") val permalink: String,
    @SerializedName("guid") val guid: String,
    @SerializedName("author") val author: String,
    @SerializedName("avatar") val avatar: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("description") val description: String?,
    @SerializedName("content") val content: String?,
    @SerializedName("enclosure") val enclosure: Enclosure,
    @SerializedName("categories") val categories: List<String>
) : DataItem<String> {
    override fun getId(): String = guid
    fun getRelativeDate(): CharSequence = DateUtils.getRelativeTimeSpanString(
        datePublished.toInstant().toEpochMilli(),
        LocalDateTime.now().getMillis(),
        DateUtils.MINUTE_IN_MILLIS
    )
}

