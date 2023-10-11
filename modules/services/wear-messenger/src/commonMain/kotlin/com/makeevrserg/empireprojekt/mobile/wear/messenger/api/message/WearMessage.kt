package com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message

interface WearMessage<T> {
    val path: String

    fun encode(value: T): ByteArray
    fun decode(byteArray: ByteArray): T
}
