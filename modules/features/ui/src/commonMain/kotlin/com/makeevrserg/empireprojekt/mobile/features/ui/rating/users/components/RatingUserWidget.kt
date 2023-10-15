package com.makeevrserg.empireprojekt.mobile.features.ui.rating.users.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import ru.astrainteractive.empireapi.models.rating.RatingUserModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RatingUserWidget(
    model: RatingUserModel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = AppTheme.dimens.XS, horizontal = AppTheme.dimens.S)
        ) {
            AsyncImage(
                model = "https://mc-heads.net/avatar/${model.minecraftUUID}",
                contentDescription = null,
                modifier = Modifier.size(54.dp).padding(AppTheme.dimens.XS)
            )
            Column {
                Text(
                    text = model.minecraftName,
                    style = AppTheme.typography.h6,
                    color = AppTheme.materialColor.onPrimary,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "${model.total}",
                    style = AppTheme.typography.subtitle1,
                    color = AppTheme.materialColor.onPrimary,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
