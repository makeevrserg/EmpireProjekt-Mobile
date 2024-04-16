package com.makeevrserg.empireprojekt.mobile.wear.messenger.api.app.message

import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.app.model.StatusModel
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.JsonWearMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.WearMessage

object StatusModelMessage {
    const val PATH = "/wear/statuses"

    object Message : WearMessage<List<StatusModel>> by JsonWearMessage(
        json = JsonWearMessage.DEFAULT_JSON,
        path = PATH
    )
}
