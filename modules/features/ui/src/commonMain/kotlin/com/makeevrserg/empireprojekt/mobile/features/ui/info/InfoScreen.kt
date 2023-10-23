package com.makeevrserg.empireprojekt.mobile.features.ui.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.makeevrserg.empireprojekt.mobile.buildkonfig.BuildKonfig
import com.makeevrserg.empireprojekt.mobile.core.ui.components.RowSettingTextInfo
import com.makeevrserg.empireprojekt.mobile.core.ui.components.navBarsPadding
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.ui.info.components.LinkWidget
import com.makeevrserg.empireprojekt.mobile.features.ui.info.data.InfoScreenLinks
import com.makeevrserg.empireprojekt.mobile.services.core.LinkBrowser

@Composable
fun InfoScreen(
    linkBrowser: LinkBrowser
) {
    val models = remember { InfoScreenLinks.get() }
    LazyColumn(
        modifier = Modifier
            .navBarsPadding()
            .padding(horizontal = AppTheme.dimens.S)
    ) {
        item {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    Modifier
                        .fillMaxWidth(0.4f)
                        .height(AppTheme.dimens.XS)
                        .clip(RoundedCornerShape(AppTheme.dimens.L))
                        .background(MaterialTheme.colors.onPrimary)
                )
            }
        }
        items(models) { linkModel ->
            LinkWidget(
                linkBrowser = linkBrowser,
                linkModel = linkModel
            )
        }
        item {
            RowSettingTextInfo(
                modifier = Modifier.padding(vertical = AppTheme.dimens.XS),
                icon = Icons.Filled.Bolt,
                text = "Version",
                endText = "${BuildKonfig.VERSION_CODE} (${BuildKonfig.VERSION_NAME})"
            )
        }
        item {
            Spacer(modifier = Modifier.height(AppTheme.dimens.M))
        }
    }
}
