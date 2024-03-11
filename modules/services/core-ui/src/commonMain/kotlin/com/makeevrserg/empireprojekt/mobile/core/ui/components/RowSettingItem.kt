package com.makeevrserg.empireprojekt.mobile.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

@Composable
fun RowSettingItem(
    modifier: Modifier = Modifier,
    text: String,
    spacing: Dp = AppTheme.dimens.S,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    prefix: (@Composable RowScope.() -> Unit)? = null,
    postfix: (@Composable RowScope.() -> Unit)? = null,
) {
    RowSettingItem(
        modifier = modifier,
        verticalAlignment = verticalAlignment,
        spacing = spacing,
        prefix = prefix,
        postfix = postfix,
        middle = {
            Text(
                text = text,
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(1f)
            )
        }
    )
}

@Composable
fun RowSettingItem(
    modifier: Modifier = Modifier,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    spacing: Dp = AppTheme.dimens.S,
    middle: (@Composable RowScope.() -> Unit)? = null,
    prefix: (@Composable RowScope.() -> Unit)? = null,
    postfix: (@Composable RowScope.() -> Unit)? = null,
) {
    Row(
        modifier = modifier,
        verticalAlignment = verticalAlignment,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        prefix?.let {
            prefix(this)
            Spacer(Modifier.width(spacing))
        }
        middle?.let {
            middle(this)
            Spacer(Modifier.width(spacing))
        }
        postfix?.invoke(this)
    }
}

@Composable
fun RowText(
    title: String,
    desc: String,
    modifier: Modifier = Modifier,
    titleColor: Color = MaterialTheme.colors.onPrimary.copy(0.5f),
    descColor: Color = MaterialTheme.colors.onPrimary
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            color = titleColor,
            textAlign = TextAlign.Start,
            modifier = Modifier,
            style = MaterialTheme.typography.subtitle1,
        )
        Text(
            text = desc,
            color = descColor,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Composable
fun RowText(
    title: String,
    desc: String,
    onDescClicked: () -> Unit,
    modifier: Modifier = Modifier,
    titleColor: Color = MaterialTheme.colors.onPrimary,
    descColor: Color = AppTheme.customColors.astraYellow
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            color = titleColor,
            textAlign = TextAlign.Start,
            modifier = Modifier,
            style = MaterialTheme.typography.subtitle1,
        )
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = desc,
                color = descColor,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .clip(RoundedCornerShape(AppTheme.dimens.XS))
                    .background(MaterialTheme.colors.primaryVariant)
                    .clickable { onDescClicked.invoke() }
                    .padding(horizontal = AppTheme.dimens.XS)
                    .padding(vertical = AppTheme.dimens.XXS),
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Suppress("LongMethod")
@Composable
fun <T> RowDropdownText(
    title: String,
    items: List<T>,
    selectedItem: T,
    toString: @Composable (T) -> String,
    onItemClicked: (T) -> Unit,
    modifier: Modifier = Modifier,
    titleColor: Color = MaterialTheme.colors.onPrimary,
    itemColor: Color = AppTheme.customColors.astraYellow
) {
    var expanded by remember { mutableStateOf(false) }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            color = titleColor,
            textAlign = TextAlign.Start,
            modifier = Modifier,
            style = MaterialTheme.typography.subtitle1,
        )
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = toString.invoke(selectedItem),
                color = itemColor,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .clip(RoundedCornerShape(AppTheme.dimens.XS))
                    .background(MaterialTheme.colors.primaryVariant)
                    .clickable { expanded = !expanded }
                    .padding(horizontal = AppTheme.dimens.XS)
                    .padding(vertical = AppTheme.dimens.XXS),
                style = MaterialTheme.typography.subtitle1
            )

            Box(modifier = Modifier.align(Alignment.CenterEnd)) {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = !expanded },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .wrapContentWidth()
                        .background(MaterialTheme.colors.primaryVariant)
                        .clickable { expanded = !expanded }
                ) {
                    items.forEachIndexed { index, item ->
                        DropdownMenuItem(
                            onClick = {
                                onItemClicked.invoke(item)
                                expanded = !expanded
                            },
                            text = {
                                Text(
                                    text = toString.invoke(item),
                                    color = MaterialTheme.colors.onPrimary,
                                    textAlign = TextAlign.End,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(AppTheme.dimens.XS))
                                        .padding(horizontal = AppTheme.dimens.XS)
                                        .padding(vertical = AppTheme.dimens.XXS),
                                    style = MaterialTheme.typography.subtitle1
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}
