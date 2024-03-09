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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.ui.components.RowText
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.ComposeTheme
import com.makeevrserg.empireprojekt.mobile.features.ui.rating.users.components.PlayerHeadBox

@Composable
fun TownCard(
    mayor: String,
    townName: String,
    board: String,
    founder: String,
    nation: String
) {
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
            title = "Founder:",
            desc = founder,
            modifier = Modifier.fillMaxWidth()
        )
        RowText(
            title = "Nation:",
            desc = nation,
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
                nation = "NCR"
            )
        }
    }
}
