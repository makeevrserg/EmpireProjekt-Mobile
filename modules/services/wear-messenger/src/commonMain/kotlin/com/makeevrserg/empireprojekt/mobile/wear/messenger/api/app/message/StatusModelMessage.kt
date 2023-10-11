package com.makeevrserg.empireprojekt.mobile.wear.messenger.api.app.message

import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.app.model.StatusModel
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.InlineWearMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.WearMessage
import kotlinx.serialization.json.Json

class StatusModelMessage(
    private val json: Json
) : WearMessage<List<StatusModel>> by InlineWearMessage(
    json = json,
    path = "/status"
)
