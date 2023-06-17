package com.makeevrserg.empireprojekt.mobile.features.status

import dev.icerock.moko.resources.desc.Raw
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EmpireProjektStatusComponent : StatusComponent {

    override val model = MutableStateFlow(
        StatusComponent.Model(
            title = StringDesc.Raw("empireprojekt.ru")
        )
    )

    private suspend fun setStatus() {
        model.update {
            it.copy(status = StatusComponent.Model.LoadingStatus.LOADING)
        }
        delay(3000)
        model.update {
            it.copy(status = StatusComponent.Model.LoadingStatus.ERROR)
        }
        delay(3000)
        model.update {
            it.copy(status = StatusComponent.Model.LoadingStatus.SUCCESS)
        }
        delay(3000)
        setStatus()
    }

    init {
        GlobalScope.launch { setStatus() }
    }
}
