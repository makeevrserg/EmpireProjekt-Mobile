package com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message

data class DecodedWearMessage<T>(
    val path: String,
    val value: T
)
