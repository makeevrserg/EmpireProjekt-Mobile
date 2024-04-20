package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.message

import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.JsonWearMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.WearMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.model.StatusModel

object StatusModelMessage {
    const val PATH = "/wear/statuses"

    object Message : WearMessage<List<StatusModel>> by JsonWearMessage(
        json = JsonWearMessage.DEFAULT_JSON,
        path = PATH
    )
}
