package com.makeevrserg.empireprojekt.mobile.features.ui.towny.towns.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.ui.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.components.filtercard.EnumOption
import com.makeevrserg.empireprojekt.mobile.core.ui.components.filtercard.FilterCard
import com.makeevrserg.empireprojekt.mobile.core.ui.components.filtercard.TextOption
import com.makeevrserg.empireprojekt.mobile.core.ui.components.filtercard.TitleOption
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.ComposeTheme
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.util.LocalSortOrderExt.toStringDesc
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.util.TownPublicTypeExt.toStringDesc
import ru.astrainteractive.empireapi.models.towny.LocalSortOrder
import ru.astrainteractive.empireapi.models.towny.TownPublicType
import ru.astrainteractive.empireapi.models.towny.TownsFilterModel

@Composable
internal fun TownFilterCard(
    townsFilter: TownsFilterModel,
    onPublicTypeClicked: () -> Unit,
    onSortByNameClicked: () -> Unit,
    onSortByTagClicked: () -> Unit,
    onSortByFounderClicked: () -> Unit,
    onSortByNationClicked: () -> Unit,
    onSortByDateClicked: () -> Unit,
    onSortByResidentsClicked: () -> Unit,
) {
    FilterCard {
        TitleOption(text = MR.strings.shared_filter.asComposableString())
        TextOption(text = MR.strings.shared_warn_multiple_filter.asComposableString())
        EnumOption(
            text = MR.strings.towns_towns_filter_public.asComposableString(),
            selected = townsFilter.publicType,
            toString = { it.toStringDesc().asComposableString() },
            onClicked = onPublicTypeClicked
        )
        EnumOption(
            text = MR.strings.towns_town_sort_by_name.asComposableString(),
            selected = townsFilter.nameSort,
            toString = { it.toStringDesc().asComposableString() },
            onClicked = onSortByNameClicked
        )
        EnumOption(
            text = MR.strings.towns_town_sort_by_tag.asComposableString(),
            selected = townsFilter.tagSort,
            toString = { it.toStringDesc().asComposableString() },
            onClicked = onSortByTagClicked
        )
        EnumOption(
            text = MR.strings.towns_town_sort_by_founder.asComposableString(),
            selected = townsFilter.founderSort,
            toString = { it.toStringDesc().asComposableString() },
            onClicked = onSortByFounderClicked
        )
        EnumOption(
            text = MR.strings.towns_town_sort_by_nation.asComposableString(),
            selected = townsFilter.nationSort,
            toString = { it.toStringDesc().asComposableString() },
            onClicked = onSortByNationClicked
        )
        EnumOption(
            text = MR.strings.towns_town_sort_by_date.asComposableString(),
            selected = townsFilter.dateSort,
            toString = { it.toStringDesc().asComposableString() },
            onClicked = onSortByDateClicked
        )
        EnumOption(
            text = MR.strings.towns_town_sort_by_residents.asComposableString(),
            selected = townsFilter.residentsSort,
            toString = { it.toStringDesc().asComposableString() },
            onClicked = onSortByResidentsClicked
        )
    }
}

@Preview
@Composable
private fun TownFilterCardPreview() {
    AdaptThemeFade(composeTheme = ComposeTheme.DARK) {
        Box(modifier = Modifier.background(MaterialTheme.colors.primaryVariant)) {
            TownFilterCard(
                townsFilter = TownsFilterModel(
                    publicType = TownPublicType.PUBLIC,
                    tagSort = LocalSortOrder.ASC,
                    nationSort = LocalSortOrder.DESC
                ),
                onSortByNationClicked = {},
                onSortByDateClicked = {},
                onSortByNameClicked = {},
                onPublicTypeClicked = {},
                onSortByTagClicked = {},
                onSortByFounderClicked = {},
                onSortByResidentsClicked = {}
            )
        }
    }
}
