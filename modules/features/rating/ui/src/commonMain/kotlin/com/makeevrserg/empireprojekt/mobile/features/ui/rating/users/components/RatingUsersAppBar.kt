package com.makeevrserg.empireprojekt.mobile.features.ui.rating.users.components

import androidx.compose.animation.Crossfade
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.ui.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.components.topbar.AstraCenterAlignedTopAppBar
import com.makeevrserg.empireprojekt.mobile.core.ui.searchbar.SearchAppBar
import com.makeevrserg.empireprojekt.mobile.core.ui.searchbar.SearchBarState
import com.makeevrserg.empireprojekt.mobile.features.rating.users.presentation.RatingUsersComponent
import com.makeevrserg.empireprojekt.mobile.services.core.PopComponent

@Composable
fun RatingUsersAppBar(
    popComponent: PopComponent,
    model: RatingUsersComponent.Model,
    onUpdateQuery: (String) -> Unit
) {
    var searchBarState by remember {
        val state = if (model.filter.query.isEmpty()) SearchBarState.Closed else SearchBarState.Open
        mutableStateOf(state)
    }
    Crossfade(searchBarState) { state ->
        if (state == SearchBarState.Open) {
            SearchAppBar(
                query = model.filter.query,
                onTextChanged = onUpdateQuery,
                onCloseClicked = {
                    onUpdateQuery.invoke("")
                    searchBarState = SearchBarState.Closed
                }
            )
        } else {
            AstraCenterAlignedTopAppBar(
                title = MR.strings.rating_user_ratings.asComposableString(),
                popComponent = popComponent,
                actions = {
                    IconButton(
                        onClick = { searchBarState = SearchBarState.Open },
                        modifier = Modifier.alpha(ContentAlpha.medium)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "",
                            tint = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            )
        }
    }
}
