package com.makeevrserg.empireprojekt.mobile.features.ui.towny.towns.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.ui.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.components.PlayerHeadBox
import com.makeevrserg.empireprojekt.mobile.core.ui.components.RowText
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.ComposeTheme
import com.makeevrserg.empireprojekt.mobile.feature.towns.TR
import kotlinx.datetime.Instant
import ru.astrainteractive.klibs.mikro.extensions.JvmTimeFormatter
import ru.astrainteractive.klibs.mikro.extensions.TimeFormatter

@Suppress("LongMethod")
@Composable
internal fun TownCard(
    mayor: String,
    townName: String,
    board: String,
    founder: String,
    nation: String,
    outlawsAmount: Int,
    tag: String,
    registered: Long,
    residentsCount: Long,
    isOpen: Boolean
) {
    val timeFormatter: TimeFormatter = JvmTimeFormatter()
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(AppTheme.dimens.XS))
            .background(MaterialTheme.colors.primary)
            .padding(
                vertical = AppTheme.dimens.XS,
                horizontal = AppTheme.dimens.S
            ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS)
            ) {
                PlayerHeadBox(
                    uuid = mayor,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(AppTheme.dimens.XXS)),
                )
                Text(
                    text = mayor,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onPrimary,
                    textAlign = TextAlign.Center
                )
            }
            Text(
                text = residentsCount.toString(),
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(AppTheme.dimens.XS))
                    .background(AppTheme.customColors.astraYellow)
                    .padding(horizontal = AppTheme.dimens.XS)
                    .padding(vertical = AppTheme.dimens.XXS)
            )
        }
        Text(
            text = townName,
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.Center
        )
        if (board.isNotBlank()) {
            Text(
                text = board,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onPrimary,
            )
        }
        RowText(
            title = TR.strings.towns_town_card_tag.asComposableString(),
            desc = tag,
            modifier = Modifier.fillMaxWidth()
        )
        RowText(
            title = TR.strings.towns_town_card_founder.asComposableString(),
            desc = founder,
            modifier = Modifier.fillMaxWidth()
        )
        if (nation.isNotBlank()) {
            RowText(
                title = TR.strings.towns_town_card_nation.asComposableString(),
                desc = nation,
                modifier = Modifier.fillMaxWidth()
            )
        }
        if (outlawsAmount > 0) {
            RowText(
                title = TR.strings.towns_town_card_outlaw_count.asComposableString(),
                desc = outlawsAmount.toString(),
                modifier = Modifier.fillMaxWidth()
            )
        }

        RowText(
            title = TR.strings.towns_town_card_registered.asComposableString(),
            desc = remember {
                timeFormatter.format(
                    instant = Instant.fromEpochMilliseconds(registered),
                    format = "dd.MM.yyyy"
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        RowText(
            title = TR.strings.towns_town_card_entrance.asComposableString(),
            desc = when (isOpen) {
                true -> TR.strings.towns_town_card_entrance_public.asComposableString()
                false -> TR.strings.towns_town_card_entrance_private.asComposableString()
            },
            descColor = when (isOpen) {
                true -> AppTheme.customColors.colorPositive
                false -> AppTheme.customColors.colorNegative
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun TownCardPreview() {
    AdaptThemeFade(composeTheme = ComposeTheme.DARK) {
        Box(modifier = Modifier.background(MaterialTheme.colors.primaryVariant)) {
            TownCard(
                mayor = "RomaRoman",
                townName = "Rostov",
                board = "Международный контролирующий орган по борьбе с пивом",
                founder = "cinnamonrein",
                nation = "NCR",
                outlawsAmount = 1,
                tag = "NCR",
                registered = 1706549308031,
                residentsCount = 10,
                isOpen = true
            )
        }
    }
}
