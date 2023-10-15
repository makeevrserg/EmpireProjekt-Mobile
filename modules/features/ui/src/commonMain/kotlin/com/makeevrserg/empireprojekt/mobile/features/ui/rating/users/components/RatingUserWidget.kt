package com.makeevrserg.empireprojekt.mobile.features.ui.rating.users.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import ru.astrainteractive.empireapi.models.rating.RatingUserModel

@Composable
fun RatingUserWidget(
    model: RatingUserModel,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(AppTheme.dimens.S))
            .background(AppTheme.materialColor.primary)
            .clickable { onClick.invoke() },
    ) {
        Column {
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
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "${model.total}",
                            style = AppTheme.typography.subtitle1,
                            color = AppTheme.materialColor.onPrimary,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
            Box(Modifier.fillMaxWidth()) {
                val gradientColors: List<Color> = listOf(
                    Color(0xFFb00000),
                    Color(0xFFb03800),
                    Color(0xFFb0aa00),
                    Color(0xFF81b000),
                    Color(0xFF2fb000),
                    Color(0xFF00b03e),
                    Color(0xFF00b0a7),
                    Color(0xFF0093b0)
                )
                val coerse = 20L
                val total = (model.total + coerse).coerceIn(0, coerse * 2)
                val progress = (total / (coerse * 2f)).coerceIn(0f, 1f)
                val backgroundColor = AppTheme.materialColor.primaryVariant
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                ) {
                    drawRect(
                        color = backgroundColor,
                        topLeft = Offset(x = 0f, y = 0f),
                        size = Size(size.width, size.height)
                    )
                    drawRect(
                        brush = Brush.horizontalGradient(
                            colors = gradientColors
                        ),
                        topLeft = Offset(x = 0f, y = 0f),
                        size = Size(progress * size.width, size.height)
                    )
                }
            }
        }
    }
}
