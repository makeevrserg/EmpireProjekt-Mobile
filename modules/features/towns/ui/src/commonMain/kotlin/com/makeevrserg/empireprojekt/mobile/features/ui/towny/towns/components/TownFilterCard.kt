package com.makeevrserg.empireprojekt.mobile.features.ui.towny.towns.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.ui.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.components.RowDropdownText
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.ComposeTheme
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.util.TownPublicTypeExt.toStringDesc
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.util.TownSortByExt.toStringDesc
import ru.astrainteractive.empireapi.models.towny.TownPublicType
import ru.astrainteractive.empireapi.models.towny.TownSortBy
import ru.astrainteractive.empireapi.models.towny.TownsFilter

@Composable
internal fun TownFilterCard(
    townsFilter: TownsFilter,
    onSortSelected: (TownSortBy) -> Unit,
    onPublicSelected: (TownPublicType) -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(AppTheme.dimens.XS))
            .background(MaterialTheme.colors.primary)
            .padding(
                vertical = AppTheme.dimens.XS,
                horizontal = AppTheme.dimens.S
            ),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS)
    ) {
        Text(
            text = MR.strings.towns_towns_filter_filter.asComposableString(),
            color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.h5,
        )
        Spacer(modifier = Modifier.height(AppTheme.dimens.XS))

        RowDropdownText(
            title = MR.strings.towns_towns_filter_public.asComposableString(),
            items = TownPublicType.entries,
            modifier = Modifier.fillMaxWidth(),
            toString = { it.toStringDesc().asComposableString() },
            selectedItem = townsFilter.publicType,
            onItemClicked = onPublicSelected
        )

        RowDropdownText(
            title = MR.strings.towns_towns_filter_sort.asComposableString(),
            items = TownSortBy.entries,
            modifier = Modifier.fillMaxWidth(),
            toString = { it.toStringDesc().asComposableString() },
            selectedItem = townsFilter.sortBy,
            onItemClicked = onSortSelected
        )
    }
}

@Preview
@Composable
private fun TownFilterCardPreview() {
    AdaptThemeFade(composeTheme = ComposeTheme.DARK) {
        Box(modifier = Modifier.background(MaterialTheme.colors.primaryVariant)) {
            TownFilterCard(
                townsFilter = TownsFilter(),
                onSortSelected = {
                },
                onPublicSelected = {
                }
            )
        }
    }
}
