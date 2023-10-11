package com.makeevrserg.empireprojekt.mobile.wear.messenger.api.producer

import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.WearMessage

interface WearMessageProducer {
    suspend fun <T> produce(message: WearMessage<T>, value: T)
}
