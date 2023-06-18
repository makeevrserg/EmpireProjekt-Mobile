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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.makeevrserg.empireprojekt.mobile.core.ui.components.RowSettingTextInfo
import com.makeevrserg.empireprojekt.mobile.core.ui.components.navBarsPadding
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.ui.info.components.LinkWidget
import com.makeevrserg.empireprojekt.mobile.features.ui.info.model.LinkModel
import com.makeevrserg.empireprojekt.mobile.services.core.LinkBrowser
import com.makeevrserg.empireprojekt.mobile.shared.BuildKonfig

@Composable
fun InfoScreen(
    linkBrowser: LinkBrowser
) {
    val models = buildList {
        LinkModel(
            title = "Astra Github",
            icon = Icons.Filled.Info,
            url = "https://github.com/Astra-Interactive/"
        ).run(::add)
        LinkModel(
            title = "Author Github",
            icon = Icons.Filled.Info,
            url = "https://github.com/makeevrserg"
        ).run(::add)
        LinkModel(
            title = "EmpireProjekt.ru",
            icon = Icons.Filled.Info,
            url = "https://EmpireProjekt.ru"
        ).run(::add)
        LinkModel(
            title = "AstraInteractive.ru",
            icon = Icons.Filled.Info,
            url = "https://AstraInteractive.ru"
        ).run(::add)
        LinkModel(
            title = "AstraLearner",
            icon = Icons.Filled.Info,
            url = "https://play.google.com/store/apps/details?id=com.makeevrserg.astralearner"
        ).run(::add)
        LinkModel(
            title = "Source code",
            icon = Icons.Filled.Info,
            url = "https://github.com/makeevrserg/EmpireProjekt-Mobile"
        ).run(::add)
        LinkModel(
            title = "Google Play page",
            icon = Icons.Filled.Info,
            url = "https://AstraInteractive.ru"
        ).run(::add)
    }
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
                        .background(AppTheme.materialColor.onPrimary)
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
