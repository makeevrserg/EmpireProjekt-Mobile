package com.makeevrserg.empireprojekt.mobile.features.ui.rating.user

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
import com.makeevrserg.empireprojekt.mobile.core.ui.components.OnEndReached
import com.makeevrserg.empireprojekt.mobile.core.ui.components.PagingWidget
import com.makeevrserg.empireprojekt.mobile.core.ui.components.topbar.AstraCenterAlignedTopAppBar
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.rating.user.presentation.RatingUserComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.rating.user.components.RatingUserWidget
import com.makeevrserg.empireprojekt.mobile.services.core.PopComponent

@Composable
fun RatingUserScreenComponent(
    popComponent: PopComponent,
    ratingUserComponent: RatingUserComponent
) {
    val model by ratingUserComponent.model.collectAsState()
    val lazyListState = rememberLazyListState()

    lazyListState.OnEndReached {
        ratingUserComponent.loadNextPage()
    }

    Scaffold(
        modifier = Modifier,
        topBar = {
            AstraCenterAlignedTopAppBar(
                title = model.reviewedUserName,
                popComponent = popComponent
            )
        }
    ) {
        LazyColumn(
            contentPadding = it,
            modifier = Modifier
                .padding(horizontal = AppTheme.dimens.XS)
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS),
            state = lazyListState
        ) {
            items(model.items) { ratingModel ->
                RatingUserWidget(
                    uuid = ratingModel.userCreatedReport?.minecraftUUID,
                    name = ratingModel.userCreatedReport?.minecraftName,
                    rating = ratingModel.rating,
                    message = ratingModel.message,
                    time = ratingModel.time
                )
            }
            item {
                PagingWidget.Auto(
                    list = model.items,
                    isLastPage = model.isLastPage,
                    isLoading = model.isLoading,
                    isFailure = model.isFailure,
                    onReload = {
                        ratingUserComponent.reset()
                    }
                )
            }
        }
    }
}
