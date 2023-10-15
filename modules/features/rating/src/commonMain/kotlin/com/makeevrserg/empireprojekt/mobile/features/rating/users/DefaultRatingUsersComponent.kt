package com.makeevrserg.empireprojekt.mobile.features.rating.users

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.makeevrserg.empireprojekt.mobile.features.rating.users.RatingUsersComponent.Model
import com.makeevrserg.empireprojekt.mobile.features.rating.users.data.RatingUsersRepository
import com.makeevrserg.empireprojekt.mobile.services.core.CoroutineFeature
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DefaultRatingUsersComponent(
    componentContext: ComponentContext,
    private val repository: RatingUsersRepository
) : RatingUsersComponent,
    ComponentContext by componentContext {
    private val coroutineFeature = instanceKeeper.getOrCreate {
        CoroutineFeature.Default()
    }
    override val model = MutableStateFlow(Model())

    private fun collectPagingState() = coroutineFeature.launch {
        repository.pagingStateFlow.collectLatest {
            model.update { model ->
                model.copy(
                    isLastPage = it.isLastPage,
                    isLoading = it.isLoading
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
        coroutineFeature.launch {
            repository.loadNextPage()
        }
    }

    init {
        collectPagingState()
        collectListStateFlow()
    }
}
