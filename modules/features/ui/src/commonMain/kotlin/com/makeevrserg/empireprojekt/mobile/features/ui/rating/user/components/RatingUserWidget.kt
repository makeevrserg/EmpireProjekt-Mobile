package com.makeevrserg.empireprojekt.mobile.features.ui.rating.user.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import ru.astrainteractive.empireapi.models.rating.RatingModel

@Composable
fun RatingUserWidget(ratingModel: RatingModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(AppTheme.dimens.S))
            .background(AppTheme.materialColor.primary)
            .padding(vertical = AppTheme.dimens.S)
            .padding(horizontal = AppTheme.dimens.XS)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.S),
                modifier = Modifier
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
            }
            Text(
                text = ratingModel.message,
                style = AppTheme.typography.subtitle2,
                color = AppTheme.materialColor.onPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )
        }
    }
}
