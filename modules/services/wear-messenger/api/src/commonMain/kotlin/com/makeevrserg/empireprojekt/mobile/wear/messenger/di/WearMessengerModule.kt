package com.makeevrserg.empireprojekt.mobile.wear.messenger.di

import android.content.Context
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.data.WearDataLayerRegistry
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.consumer.WearMessageConsumer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.consumer.WearMessageConsumerImpl
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.producer.WearMessageProducer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.producer.WearMessageProducerImpl
import kotlinx.coroutines.CoroutineScope

interface WearMessengerModule {
    val wearMessageProducer: WearMessageProducer
    val wearMessageConsumer: WearMessageConsumer

    class Default(
        context: Context,
        coroutineScope: CoroutineScope,
    ) : WearMessengerModule {
        @OptIn(ExperimentalHorologistApi::class)
        private val wearDataLayerRegistry by lazy {
            WearDataLayerRegistry.fromContext(
                application = context,
                coroutineScope = coroutineScope
            )
        }

        @OptIn(ExperimentalHorologistApi::class)
        private val messageClient by lazy {
            wearDataLayerRegistry.messageClient
        }

        @OptIn(ExperimentalHorologistApi::class)
        override val wearMessageProducer: WearMessageProducer by lazy {
            WearMessageProducerImpl(
                wearDataLayerRegistry = wearDataLayerRegistry,
                messageClient = messageClient
            )
        }

        @OptIn(ExperimentalHorologistApi::class)
        override val wearMessageConsumer: WearMessageConsumer by lazy {
            WearMessageConsumerImpl(
                wearDataLayerRegistry = wearDataLayerRegistry,
                messageClient = messageClient
            )
        }
    }
}
