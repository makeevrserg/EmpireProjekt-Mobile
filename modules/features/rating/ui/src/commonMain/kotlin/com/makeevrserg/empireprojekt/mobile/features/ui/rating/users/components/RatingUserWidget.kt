package com.makeevrserg.empireprojekt.mobile.features.ui.rating.users.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.ui.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.components.PlayerHeadBox
import com.makeevrserg.empireprojekt.mobile.core.ui.components.RowText
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.ComposeTheme
import com.makeevrserg.empireprojekt.mobile.rating.RR
import kotlinx.datetime.Instant
import ru.astrainteractive.empireapi.models.rating.RatingUserModel
import ru.astrainteractive.klibs.mikro.extensions.JvmTimeFormatter
import ru.astrainteractive.klibs.mikro.extensions.TimeFormatter

@Suppress("LongMethod")
@Composable
internal fun RatingUserWidget(
    model: RatingUserModel,
    onClick: () -> Unit
) {
    val timeFormatter: TimeFormatter = JvmTimeFormatter()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(AppTheme.dimens.S))
            .background(MaterialTheme.colors.primary)
            .clickable { onClick.invoke() },
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = AppTheme.dimens.XS, horizontal = AppTheme.dimens.S),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PlayerHeadBox(
                            uuid = model.minecraftUUID,
                            modifier = Modifier
                                .size(32.dp)
                                .clip(RoundedCornerShape(AppTheme.dimens.XXS)),
                        )
                        Spacer(Modifier.width(AppTheme.dimens.XS))
                        Text(
                            text = model.minecraftName,
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.onPrimary,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.weight(1f))
                        Icon(
                            imageVector = Icons.Filled.ChevronRight,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onPrimary,
                            modifier = Modifier.size(AppTheme.dimens.M)
                        )
                    }
                    RowText(
                        title = RR.strings.rating_rating.asComposableString(),
                        desc = "${model.totalRating}",
                        modifier = Modifier.fillMaxWidth()
                    )
                    RowText(
                        title = RR.strings.rating_votes_count.asComposableString(),
                        desc = "${model.ratingVotes}",
                        modifier = Modifier.fillMaxWidth()
                    )
                    RowText(
                        title = RR.strings.rating_last_updated.asComposableString(),
                        desc = remember {
                            timeFormatter.format(
                                instant = Instant.fromEpochMilliseconds(model.lastUpdated),
                                format = "dd.MM.yyyy"
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            GradientProgressIndicator(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFb00000),
                        Color(0xFFb03800),
                        Color(0xFFb0aa00),
                        Color(0xFF81b000),
                        Color(0xFF2fb000),
                        Color(0xFF00b03e),
                        Color(0xFF00b0a7),
                        Color(0xFF0093b0)
                    )
                ),
                progress = let {
                    val coerse = 10L
                    val total = (model.totalRating + coerse).coerceIn(0, coerse * 2)
                    (total / (coerse * 2f)).coerceIn(0f, 1f)
                }
            )
        }
    }
}

@Composable
@Preview
private fun RatingUserWidgetPreview() {
    AdaptThemeFade(composeTheme = ComposeTheme.DARK) {
        RatingUserWidget(
            model = RatingUserModel(
                id = 1,
                minecraftUUID = "uuid",
                minecraftName = "name",
                lastUpdated = 0,
                totalRating = 10,
                ratingVotes = 1
            ),
            onClick = {}
        )
    }
}
