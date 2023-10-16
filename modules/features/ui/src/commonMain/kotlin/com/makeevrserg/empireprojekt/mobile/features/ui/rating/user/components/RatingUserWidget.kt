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
import coil.compose.AsyncImage
import com.makeevrserg.empireprojekt.mobile.core.ui.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.resources.MR
import ru.astrainteractive.empireapi.models.rating.RatingModel

@Composable
fun RatingUserWidget(ratingModel: RatingModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(AppTheme.dimens.S))
            .background(AppTheme.materialColor.primary)
            .padding(vertical = AppTheme.dimens.XS)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.S),
                modifier = Modifier.padding(horizontal = AppTheme.dimens.S)
            ) {
                AsyncImage(
                    model = "https://mc-heads.net/avatar/${ratingModel.userCreatedReport?.minecraftUUID}",
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    text = ratingModel.userCreatedReport?.minecraftName ?: "-",
                    style = AppTheme.typography.subtitle2,
                    color = AppTheme.materialColor.onPrimary,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.weight(1f))
                when {
                    ratingModel.rating > 0 -> {
                        Icon(
                            imageVector = Icons.Filled.ThumbUp,
                            contentDescription = null,
                            tint = AppTheme.alColors.colorPositive,
                            modifier = Modifier.size(AppTheme.dimens.M)
                        )
                    }

                    else -> {
                        Icon(
                            imageVector = Icons.Filled.ThumbDown,
                            contentDescription = null,
                            tint = AppTheme.alColors.colorNegative,
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
                    .background(AppTheme.materialColor.onSecondary)
            )
            Spacer(Modifier.height(AppTheme.dimens.XS))
            Text(
                text = MR.strings.rating_player_message.asComposableString(),
                style = AppTheme.typography.subtitle2,
                color = AppTheme.materialColor.onSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = AppTheme.dimens.S)
            )
            Text(
                text = ratingModel.message.trim(),
                style = AppTheme.typography.subtitle2,
                color = AppTheme.materialColor.onPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = AppTheme.dimens.S)
            )
        }
    }
}
