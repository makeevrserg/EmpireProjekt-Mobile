package com.makeevrserg.empireprojekt.mobile.wear.messenger.api.consumer

import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.DecodedWearMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.WearMessage
import kotlinx.coroutines.flow.Flow

interface WearMessageConsumer {
    val messagesFlow: Flow<DecodedWearMessage<*>>
    suspend fun <T> consume(message: WearMessage<T>, byteArray: ByteArray)
}
