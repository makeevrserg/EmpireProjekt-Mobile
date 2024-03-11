package com.makeevrserg.empireprojekt.mobile.features.ui.rating.users

import androidx.compose.foundation.layout.Arrangement
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
import com.makeevrserg.empireprojekt.mobile.features.rating.users.RatingUsersComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.rating.users.components.RatingUserWidget
import com.makeevrserg.empireprojekt.mobile.services.core.PopComponent

@Composable
fun RatingUsersScreenComponent(
    popComponent: PopComponent,
    ratingUsersComponent: RatingUsersComponent
) {
    val model by ratingUsersComponent.model.collectAsState()
    val lazyListState = rememberLazyListState()
    lazyListState.OnEndReached {
        ratingUsersComponent.loadNextPage()
    }

    Scaffold(
        modifier = Modifier,
        topBar = {
            AstraCenterAlignedTopAppBar(
                title = MR.strings.rating_user_ratings.asComposableString(),
                popComponent = popComponent
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = AppTheme.dimens.XS),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS),
            contentPadding = it,
            state = lazyListState
        ) {
            items(model.items) { ratingUserModel ->
                RatingUserWidget(
                    model = ratingUserModel,
                    onClick = {
                        ratingUsersComponent.showUserRatings(
                            ratingUserModel.id,
                            ratingUserModel.minecraftName
                        )
                    }
                )
            }

            item {
                PagingWidget.Auto(
                    list = model.items,
                    isLastPage = model.isLastPage,
                    isLoading = model.isLoading,
                    isFailure = model.isFailure,
                    onReload = {
                        ratingUsersComponent.reset()
                    }
                )
            }
        }
    }
}
