package com.makeevrserg.empireprojekt.mobile.features.ui.towny.towns

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.ui.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.components.OnEndReached
import com.makeevrserg.empireprojekt.mobile.core.ui.components.PagingWidget
import com.makeevrserg.empireprojekt.mobile.core.ui.components.topbar.AstraCenterAlignedTopAppBar
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.presentation.TownsComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.towny.towns.components.TownCard
import com.makeevrserg.empireprojekt.mobile.features.ui.towny.towns.components.TownFilterCard
import com.makeevrserg.empireprojekt.mobile.services.core.PopComponent

@Suppress("LongMethod")
@Composable
fun TownsScreenComponent(
    popComponent: PopComponent,
    townsComponent: TownsComponent
) {
    val model by townsComponent.model.collectAsState()
    val lazyListState = rememberLazyListState()
    lazyListState.OnEndReached {
        townsComponent.loadNextPage()
    }

    Scaffold(
        modifier = Modifier,
        topBar = {
            AstraCenterAlignedTopAppBar(
                title = MR.strings.towns_title.asComposableString(),
                popComponent = popComponent
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = AppTheme.dimens.XS)
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS),
            contentPadding = it,
            state = lazyListState
        ) {
            item {
                TownFilterCard(
                    townsFilter = model.filter,
                    onSortByNationClicked = townsComponent::nextNationSort,
                    onSortByResidentsClicked = townsComponent::nextResidentsSort,
                    onSortByTagClicked = townsComponent::nextTagSort,
                    onSortByDateClicked = townsComponent::nextDateSort,
                    onSortByNameClicked = townsComponent::nextNameSort,
                    onPublicTypeClicked = townsComponent::nextPublicType,
                    onSortByFounderClicked = townsComponent::nextFounderSort
                )
            }
            items(model.items) { townModel ->
                TownCard(
                    mayor = townModel.mayor,
                    townName = townModel.name,
                    board = townModel.townBoard,
                    founder = townModel.founder,
                    nation = townModel.nation,
                    outlawsAmount = townModel.outlaws.size,
                    tag = townModel.tag,
                    registered = townModel.registered,
                    residentsCount = townModel.residentsCount,
                    isOpen = townModel.open
                )
            }

            item {
                PagingWidget.Auto(
                    list = model.items,
                    isLastPage = model.isLastPage,
                    isLoading = model.isLoading,
                    isFailure = model.isFailure,
                    onReload = {
                        townsComponent.reset()
                    }
                )
            }
        }
    }
}
