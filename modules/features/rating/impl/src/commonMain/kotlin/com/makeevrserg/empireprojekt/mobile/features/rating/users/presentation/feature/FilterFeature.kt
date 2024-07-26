package com.makeevrserg.empireprojekt.mobile.features.rating.users.presentation.feature

import com.makeevrserg.empireprojekt.mobile.features.rating.users.storage.RatingsFilterStorageValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.astrainteractive.empireapi.models.rating.RatingsFilterModel
import ru.astrainteractive.klibs.kstorage.util.KrateExt.update
import ru.astrainteractive.klibs.mikro.extensions.arkivanov.CoroutineFeature

internal class FilterFeature(
    private val ratingsFilterStorageValue: RatingsFilterStorageValue
) : CoroutineFeature by CoroutineFeature.Main(), RatingUsersFeature.FilterProvider {
    private val _state = MutableStateFlow(ratingsFilterStorageValue.cachedValue.copy(query = ""))
    override val filterStateFlow = _state.asStateFlow()

    fun updateFilter(block: (RatingsFilterModel) -> RatingsFilterModel) {
        _state.update(block)
        ratingsFilterStorageValue.save(filterStateFlow.value.copy(query = ""))
    }
}
