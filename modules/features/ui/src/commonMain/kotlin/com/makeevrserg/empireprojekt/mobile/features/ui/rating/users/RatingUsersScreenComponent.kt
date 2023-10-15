package com.makeevrserg.empireprojekt.mobile.features.ui.rating.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
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
import com.makeevrserg.empireprojekt.mobile.core.ui.components.topbar.AstraCenterAlignedTopAppBar
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.rating.users.RatingUsersComponent
import com.makeevrserg.empireprojekt.mobile.features.root.RootComponent
import com.makeevrserg.empireprojekt.mobile.features.root.screen.RootScreenComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.rating.users.components.RatingUserWidget

@Composable
fun RatingUsersScreenComponent(
    rootComponent: RootComponent,
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
        topBar = {
            AstraCenterAlignedTopAppBar(
                title = "User ratings",
                onBackClicked = rootComponent.rootScreenComponent::pop
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = AppTheme.dimens.XS).navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS),
            contentPadding = it
        ) {
            items(model.items) { ratingUserModel ->
                RatingUserWidget(
                    model = ratingUserModel,
                    onClick = {
                        val configuration = RootScreenComponent.Child.RatingUser(ratingUserModel.id)
                        rootComponent.rootScreenComponent.push(configuration)
                    }
                )
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
