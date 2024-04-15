package com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message

import android.util.Log
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Suppress("FunctionNaming")
inline fun <reified T> JsonWearMessage(
    json: Json,
    path: String
): WearMessage<T> = InlineWearMessage(
    path = path,
    encode = { value ->
        val string = json.encodeToString(value)
        Log.d("InlineWearMessage", "InlineWearMessage->encode: $string")
        string.toByteArray()
    },
    decode = { byteArray ->
        val string = byteArray.decodeToString()
        Log.d("InlineWearMessage", "InlineWearMessage->decode: $string")
        json.decodeFromString(string)
    }
)
