package com.makeevrserg.empireprojekt.mobile.features.ui.rating.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.core.ui.components.PagingWidget
import com.makeevrserg.empireprojekt.mobile.core.ui.components.rememberIsScrolledToTheEnd
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.rating.users.RatingUsersComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.rating.user.components.RatingUserWidget

@Composable
fun RatingUsersScreenComponent(
    ratingUsersComponent: RatingUsersComponent
) {
    val model by ratingUsersComponent.model.collectAsState()
    val lazyListState = rememberLazyListState()
    val isScrolledToEnd by lazyListState.rememberIsScrolledToTheEnd()

    LaunchedEffect(isScrolledToEnd) {
        if (isScrolledToEnd) {
            ratingUsersComponent.loadNextPage()
        }
    }
    LaunchedEffect(ratingUsersComponent) {
        ratingUsersComponent.loadNextPage()
    }

    Scaffold(
        modifier = Modifier,
    ) {
        LazyColumn(
            contentPadding = it,
            modifier = Modifier.systemBarsPadding().padding(horizontal = AppTheme.dimens.XS),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS)
        ) {
            items(model.items) { ratingUserModel ->
                RatingUserWidget(ratingUserModel)
            }

            item {
                PagingWidget.Auto(
                    list = model.items,
                    isLastPage = model.isLastPage,
                    isLoading = model.isLoading,
                    onReload = {
                        ratingUsersComponent.reset()
                    }
                )
            }
        }
    }
}
