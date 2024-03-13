package com.makeevrserg.empireprojekt.mobile.features.rating.user.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.makeevrserg.empireprojekt.mobile.features.rating.user.data.RatingUserRepository
import com.makeevrserg.empireprojekt.mobile.features.rating.user.presentation.RatingUserComponent.Model
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.astrainteractive.empireapi.models.rating.UserRatingsRequest
import ru.astrainteractive.klibs.mikro.extensions.arkivanov.CoroutineFeature

internal class DefaultRatingUserComponent(
    componentContext: ComponentContext,
    userId: Long,
    userName: String,
    private val ratingUserRepository: RatingUserRepository
) : RatingUserComponent,
    ComponentContext by componentContext {
    private val coroutineFeature = instanceKeeper.getOrCreate {
        CoroutineFeature.Main()
    }
    override val model = MutableStateFlow(
        Model(
            request = UserRatingsRequest(id = userId),
            reviewedUserName = userName
        )
    )

    private fun collectPagingState() = coroutineFeature.launch {
        ratingUserRepository.state.collectLatest {
            model.update { model ->
                model.copy(
                    isLastPage = it.isLastPage,
                    isLoading = it.isLoading,
                    isFailure = it.isFailure,
                    items = it.items
                )
            }
        }
    }

    override fun reset() {
        coroutineFeature.launch {
            ratingUserRepository.reset()
            ratingUserRepository.loadNextPage()
        }
    }

    override fun loadNextPage() {
        coroutineFeature.launch {
            ratingUserRepository.loadNextPage()
        }
    }

    init {
        ratingUserRepository.updateRequest(model.value.request)
        collectPagingState()
    }
}
