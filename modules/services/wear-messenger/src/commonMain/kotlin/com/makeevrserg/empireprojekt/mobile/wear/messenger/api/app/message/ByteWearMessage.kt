package com.makeevrserg.empireprojekt.mobile.wear.messenger.api.app.message

import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.InlineWearMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.WearMessage

object ByteWearMessage {
    const val PATH = "/wear/ping"

    object Message : WearMessage<Byte> by InlineWearMessage(
        path = PATH,
        encode = { value ->
            byteArrayOf(value)
        },
        decode = { byteArray ->
            byteArray.first()
        }
    )
}
