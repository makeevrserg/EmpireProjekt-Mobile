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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.ui.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.components.PlayerHeadBox
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import ru.astrainteractive.empireapi.models.rating.RatingModel

@Suppress("LongMethod")
@Composable
internal fun RatingUserWidget(ratingModel: RatingModel) {
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
                    uuid = ratingModel.userCreatedReport?.minecraftUUID.orEmpty(),
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(AppTheme.dimens.XXS)),
                )
                Text(
                    text = ratingModel.userCreatedReport?.minecraftName ?: "-",
                    style = MaterialTheme.typography.subtitle2,
                    color = MaterialTheme.colors.onPrimary,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.weight(1f))
                when {
                    ratingModel.rating > 0 -> {
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
            Text(
                text = MR.strings.rating_player_message.asComposableString(),
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.onSecondary,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = AppTheme.dimens.S)
            )
            Text(
                text = ratingModel.message.trim(),
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = AppTheme.dimens.S)
            )
        }
    }
}
