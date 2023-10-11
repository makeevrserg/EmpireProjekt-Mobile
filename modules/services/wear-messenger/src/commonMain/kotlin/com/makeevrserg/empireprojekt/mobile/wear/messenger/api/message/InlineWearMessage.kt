package com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message

import android.util.Log
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class InlineWearMessage<T>(
    override val path: String,
    private val encode: (T) -> ByteArray,
    private val decode: (ByteArray) -> T
) : WearMessage<T> {
    override fun encode(value: T): ByteArray = this.encode.invoke(value)

    override fun decode(byteArray: ByteArray): T = this.decode.invoke(byteArray)
}

@Suppress("FunctionNaming")
inline fun <reified T> InlineWearMessage(
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
