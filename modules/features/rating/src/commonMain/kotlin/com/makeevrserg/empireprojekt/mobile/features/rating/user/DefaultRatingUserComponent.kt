package com.makeevrserg.empireprojekt.mobile.features.rating.user

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.makeevrserg.empireprojekt.mobile.features.rating.user.RatingUserComponent.Model
import com.makeevrserg.empireprojekt.mobile.features.rating.user.data.RatingUserRepository
import com.makeevrserg.empireprojekt.mobile.services.core.CoroutineFeature
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.astrainteractive.empireapi.models.rating.UserRatingsRequest

class DefaultRatingUserComponent(
    componentContext: ComponentContext,
    userId: Long,
    userName: String,
    private val repository: RatingUserRepository
) : RatingUserComponent,
    ComponentContext by componentContext {
    private val coroutineFeature = instanceKeeper.getOrCreate {
        CoroutineFeature.Default()
    }
    override val model = MutableStateFlow(
        Model(
            request = UserRatingsRequest(id = userId),
            reviewedUserName = userName
        )
    )

    private fun collectPagingState() = coroutineFeature.launch {
        repository.pagingStateFlow.collectLatest {
            model.update { model ->
                model.copy(
                    isLastPage = it.isLastPage,
                    isLoading = it.isLoading,
                    isFailure = it.isFailure
                )
            }
        }
    }

    private fun collectListStateFlow() = coroutineFeature.launch {
        repository.listStateFlow.collectLatest {
            model.update { model ->
                model.copy(items = it)
            }
        }
    }

    override fun reset() {
        coroutineFeature.launch {
            repository.reset()
            repository.loadNextPage()
        }
    }

    override fun loadNextPage() {
        println("LoadingNextPage")
        coroutineFeature.launch {
            repository.loadNextPage()
        }
    }

    init {
        repository.updateRequest(model.value.request)
        collectPagingState()
        collectListStateFlow()
    }
}
