package com.makeevrserg.empireprojekt.mobile.features.ui.info.components

import androidx.compose.runtime.Composable
import com.makeevrserg.empireprojekt.mobile.core.ui.components.RowSettingChevronItem
import com.makeevrserg.empireprojekt.mobile.features.ui.info.model.LinkModel
import com.makeevrserg.empireprojekt.mobile.services.core.LinkBrowser

@Composable
internal fun LinkWidget(
    linkBrowser: LinkBrowser,
    linkModel: LinkModel
) {
    RowSettingChevronItem(
        icon = linkModel.res,
        text = linkModel.title,
        tint = linkModel.tint.invoke(),
        onClick = {
            linkBrowser.openInBrowser(linkModel.url)
        }
    )
}
