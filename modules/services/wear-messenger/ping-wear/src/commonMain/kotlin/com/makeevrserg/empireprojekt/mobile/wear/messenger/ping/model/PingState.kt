package com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.model

sealed interface PingState {
    /**
     * @param timestamp last time when ping were received
     */
    class Connected(val timestamp: Long) : PingState

    /**
     * @param timestamp last time when ping were received
     */
    class NotConnected(val timestamp: Long) : PingState
}
