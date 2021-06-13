package com.mpdevelopment.hiltcoroutine.network.adapter

import com.google.gson.*
import java.lang.reflect.Type
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Note that the time zone is serialized/deserialized as is. To get local time use
 * [ZonedDateTime.withZoneSameInstant] with [ZoneId.systemDefault] for zone id.
 */
class ZonedDateTimeTypeAdapter :
    JsonSerializer<ZonedDateTime>,
    JsonDeserializer<ZonedDateTime> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): ZonedDateTime {
        return ZonedDateTime.parse(
            json.asString, DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(
                ZoneId.systemDefault()
            )
        )
    }

    override fun serialize(
        src: ZonedDateTime,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        return JsonPrimitive(src.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
    }
}