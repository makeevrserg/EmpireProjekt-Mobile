package com.makeevrserg.empireprojekt.mobile.features.ui.rating.user.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.ui.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.components.PlayerHeadBox
import com.makeevrserg.empireprojekt.mobile.core.ui.components.RowText
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import ru.astrainteractive.klibs.mikro.extensions.JvmTimeFormatter
import ru.astrainteractive.klibs.mikro.extensions.TimeFormatter
import java.util.UUID

@Suppress("LongMethod")
@Composable
internal fun RatingUserWidget(
    uuid: String?,
    name: String?,
    rating: Int,
    message: String,
    time: Long
) {
    val timeFormatter: TimeFormatter = JvmTimeFormatter()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(AppTheme.dimens.S))
            .background(MaterialTheme.colors.primary)
            .padding(vertical = AppTheme.dimens.XS)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.S),
                modifier = Modifier.padding(horizontal = AppTheme.dimens.S)
            ) {
                PlayerHeadBox(
                    uuid = uuid.orEmpty(),
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(AppTheme.dimens.XXS)),
                )
                Text(
                    text = name ?: "-",
                    style = MaterialTheme.typography.subtitle2,
                    color = MaterialTheme.colors.onPrimary,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.weight(1f))
                when {
                    rating > 0 -> {
                        Icon(
                            imageVector = Icons.Filled.ThumbUp,
                            contentDescription = null,
                            tint = AppTheme.customColors.colorPositive,
                            modifier = Modifier.size(AppTheme.dimens.M)
                        )
                    }

                    else -> {
                        Icon(
                            imageVector = Icons.Filled.ThumbDown,
                            contentDescription = null,
                            tint = AppTheme.customColors.colorNegative,
                            modifier = Modifier.size(AppTheme.dimens.M)
                        )
                    }
                }
            }
            Spacer(Modifier.height(AppTheme.dimens.XS))
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colors.onSecondary)
            )
            Spacer(Modifier.height(AppTheme.dimens.XS))
            RowText(
                title = MR.strings.rating_last_updated.asComposableString(),
                desc = remember {
                    timeFormatter.format(
                        instant = Instant.fromEpochMilliseconds(time),
                        format = "dd.MM.yyyy HH:mm"
                    )
                },
                modifier = Modifier.fillMaxWidth().padding(horizontal = AppTheme.dimens.S)
            )
            Text(
                text = MR.strings.rating_player_message.asComposableString(),
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.onSecondary,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = AppTheme.dimens.S)
            )
            Text(
                text = message.trim(),
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = AppTheme.dimens.S)
            )
        }
    }
}

@Preview
@Composable
private fun RatingUserWidgetPreview() {
    AdaptThemeFade {
        RatingUserWidget(
            uuid = UUID.randomUUID().toString(),
            name = "RomaRoman",
            rating = 10,
            message = "Hello world",
            time = Clock.System.now().toEpochMilliseconds()
        )
    }
}
