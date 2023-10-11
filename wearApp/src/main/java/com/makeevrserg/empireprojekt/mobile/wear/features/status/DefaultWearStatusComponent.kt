package com.makeevrserg.empireprojekt.mobile.wear.features.status

import com.makeevrserg.empireprojekt.mobile.features.status.StatusComponent
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.app.model.StatusModel
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.DecodedWearMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.receiver.WearMessageReceiver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DefaultWearStatusComponent(
    wearMessageReceiver: WearMessageReceiver,
    coroutineScope: CoroutineScope
) : WearStatusComponent {
    override val mergedState: StateFlow<WearStatusComponent.Model> =
        wearMessageReceiver.messagesFlow
            .filterIsInstance<DecodedWearMessage<List<StatusModel>>>()
            .map { it.value }
            .map { statusModels ->
                WearStatusComponent.Model(
                    loadingCount = statusModels.count {
                        it.isLoading || it.status == StatusModel.LoadingStatus.LOADING
                    },
                    successCount = statusModels.count { it.status == StatusModel.LoadingStatus.SUCCESS },
                    failureCount = statusModels.count { it.status == StatusModel.LoadingStatus.ERROR }
                )
            }.stateIn(coroutineScope, SharingStarted.Eagerly, WearStatusComponent.Model())

    override fun update(status: StatusComponent.Model.LoadingStatus, amount: Int) {
        // todo
    }
}
