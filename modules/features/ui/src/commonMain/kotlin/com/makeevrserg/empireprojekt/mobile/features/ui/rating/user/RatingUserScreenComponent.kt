package com.makeevrserg.empireprojekt.mobile.features.ui.rating.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.makeevrserg.empireprojekt.mobile.core.ui.components.OnEndReached
import com.makeevrserg.empireprojekt.mobile.core.ui.components.PagingWidget
import com.makeevrserg.empireprojekt.mobile.core.ui.components.topbar.AstraCenterAlignedTopAppBar
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.rating.user.RatingUserComponent
import com.makeevrserg.empireprojekt.mobile.features.root.RootComponent

@Composable
fun RatingUserScreenComponent(
    rootComponent: RootComponent,
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
                onBackClicked = rootComponent.rootScreenComponent::pop
            )
        }
    ) {
        LazyColumn(
            contentPadding = it,
            modifier = Modifier.padding(horizontal = AppTheme.dimens.XS).navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS),
            state = lazyListState
        ) {
            items(model.items) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(AppTheme.dimens.S))
                        .background(AppTheme.materialColor.primary)
                        .padding(vertical = AppTheme.dimens.S)
                        .padding(horizontal = AppTheme.dimens.XS)
                ) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.S),
                            modifier = Modifier
                        ) {
                            AsyncImage(
                                model = "https://mc-heads.net/avatar/${it.userCreatedReport?.minecraftUUID}",
                                contentDescription = null,
                                modifier = Modifier.size(32.dp)
                            )
                            Text(
                                text = it.userCreatedReport?.minecraftName ?: "-",
                                style = AppTheme.typography.subtitle2,
                                color = AppTheme.materialColor.onPrimary,
                                textAlign = TextAlign.Center
                            )
                        }
                        Text(
                            text = it.message,
                            style = AppTheme.typography.subtitle2,
                            color = AppTheme.materialColor.onPrimary,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                        )
                    }
                }
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
