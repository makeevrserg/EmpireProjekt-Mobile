package com.makeevrserg.empireprojekt.mobile.features.ui.info

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.buildkonfig.BuildKonfig
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.ui.asComposableString
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
            .padding(horizontal = AppTheme.dimens.S)
    ) {
        item {
            Text(
                text = MR.strings.info_more_links.asComposableString(),
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onPrimary,
            )
        }
        item {
            Text(
                text = "All this links associated with EmpireProjekt and AstraInteractive. " +
                    "This text specially displayed for google play " +
                    "support which considering this links as advertisement.",
                style = MaterialTheme.typography.overline,
                color = MaterialTheme.colors.onPrimary
            )
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
            Spacer(modifier = Modifier.navBarsPadding())
        }
    }
}
