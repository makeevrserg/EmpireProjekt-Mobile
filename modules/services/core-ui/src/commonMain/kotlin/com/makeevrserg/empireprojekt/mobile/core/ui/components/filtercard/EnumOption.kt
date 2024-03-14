package com.makeevrserg.empireprojekt.mobile.core.ui.components.filtercard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

@Composable
fun <E : Enum<E>> FilterCardScope.EnumOption(
    text: String,
    selected: E?,
    toString: @Composable (E) -> String,
    onClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(AppTheme.dimens.XS))
            .clickable { onClicked.invoke() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.Start,
            modifier = Modifier,
            style = MaterialTheme.typography.subtitle1,
        )
        Text(
            text = selected?.let { toString.invoke(it) } ?: "-",
            color = MaterialTheme.colors.secondaryVariant,
            textAlign = TextAlign.End,
            modifier = Modifier
                .weight(1f),
            style = MaterialTheme.typography.subtitle1
        )
    }
}
