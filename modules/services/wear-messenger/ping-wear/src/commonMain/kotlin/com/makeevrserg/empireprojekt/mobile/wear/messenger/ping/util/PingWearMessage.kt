package com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.util

import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.InlineWearMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.WearMessage

object PingWearMessage {
    const val PATH = "/wear/ping"

    /**
     * Delay between each ping
     */
    const val DELAY = 5_000L

    /**
     * Debounce after which device is not connected
     */
    const val DEBOUNCE = 20_000L

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
