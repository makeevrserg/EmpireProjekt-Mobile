package com.makeevrserg.empireprojekt.mobile.wear.messenger.api.receiver

import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.DecodedWearMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.WearMessage
import kotlinx.coroutines.flow.Flow

interface WearMessageReceiver {
    val messagesFlow: Flow<DecodedWearMessage<*>>
    suspend fun <T> consume(message: WearMessage<T>, byteArray: ByteArray)
}
