package com.makeevrserg.empireprojekt.mobile.features.ui.rating.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.makeevrserg.empireprojekt.mobile.core.ui.components.PagingWidget
import com.makeevrserg.empireprojekt.mobile.core.ui.components.rememberIsScrolledToTheEnd
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.rating.user.RatingUserComponent

@Composable
fun RatingUserScreenComponent(
    ratingUserComponent: RatingUserComponent
) {
    val model by ratingUserComponent.model.collectAsState()
    val lazyListState = rememberLazyListState()
    val isScrolledToEnd by lazyListState.rememberIsScrolledToTheEnd()

    LaunchedEffect(isScrolledToEnd) {
        if (isScrolledToEnd) {
            ratingUserComponent.loadNextPage()
        }
    }
    LaunchedEffect(ratingUserComponent) {
        ratingUserComponent.loadNextPage()
    }

    Scaffold(
        modifier = Modifier,
    ) {
        LazyColumn(
            contentPadding = it,
            modifier = Modifier.systemBarsPadding().padding(horizontal = AppTheme.dimens.XS),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS)
        ) {
            items(model.items) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier.padding(vertical = AppTheme.dimens.XS, horizontal = AppTheme.dimens.XS)
                        ) {
                            AsyncImage(
                                model = "https://mc-heads.net/avatar/${it.userCreatedReport?.minecraftUUID}",
                                contentDescription = null,
                                modifier = Modifier.size(54.dp).padding(AppTheme.dimens.XS)
                            )
                            Text(
                                text = it.userCreatedReport?.minecraftName ?: "-",
                                style = AppTheme.typography.h6,
                                color = AppTheme.materialColor.onPrimary,
                                textAlign = TextAlign.Center
                            )
                        }
                        Text(
                            text = it.message,
                            style = AppTheme.typography.subtitle2,
                            color = AppTheme.materialColor.onPrimary,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = AppTheme.dimens.XS)
                        )
                    }
                }
            }
            item {
                PagingWidget.Auto(
                    list = model.items,
                    isLastPage = model.isLastPage,
                    isLoading = model.isLoading,
                    onReload = {
                        ratingUserComponent.reset()
                    }
                )
            }
        }
    }
}
