package com.mpdevelopment.hiltcoroutine.network.adapter

import com.google.gson.*
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * GSON serializer/deserializer for converting [LocalDateTime] objects.
 */
class LocalDateTimeTypeAdapter :
    JsonSerializer<LocalDateTime?>,
    JsonDeserializer<LocalDateTime> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): LocalDateTime {
        return LocalDateTime.parse(json.asString, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    }

    override fun serialize(
        src: LocalDateTime?,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        return JsonPrimitive(src!!.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
    }
}