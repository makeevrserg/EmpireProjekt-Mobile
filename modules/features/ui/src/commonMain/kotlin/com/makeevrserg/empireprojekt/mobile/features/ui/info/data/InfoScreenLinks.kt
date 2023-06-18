package com.makeevrserg.empireprojekt.mobile.features.ui.info.data

import com.makeevrserg.empireprojekt.mobile.features.ui.info.model.LinkModel
import com.makeevrserg.empireprojekt.mobile.resources.MR

object InfoScreenLinks {
    fun get(): List<LinkModel> {
        return buildList {
            LinkModel(
                title = "Astra Github",
                res = MR.images.github,
                url = "https://github.com/Astra-Interactive/"
            ).run(::add)
            LinkModel(
                title = "Author Github",
                res = MR.images.github,
                url = "https://github.com/makeevrserg"
            ).run(::add)
            LinkModel(
                title = "Source code",
                res = MR.images.github,
                url = "https://github.com/makeevrserg/EmpireProjekt-Mobile"
            ).run(::add)
            LinkModel(
                title = "EmpireProjekt.ru",
                res = MR.images.ic_splash,
                url = "https://EmpireProjekt.ru"
            ).run(::add)
            LinkModel(
                title = "AstraInteractive.ru",
                res = MR.images.ainteractivelogo,
                url = "https://AstraInteractive.ru"
            ).run(::add)
            LinkModel(
                title = "AstraLearner",
                res = MR.images.alearner,
                url = "https://play.google.com/store/apps/details?id=com.makeevrserg.astralearner"
            ).run(::add)
            LinkModel(
                title = "Google Play page",
                res = MR.images.gplay,
                url = "https://play.google.com/store/apps/details?id=com.makeevrserg.empireprojekt.mobile"
            ).run(::add)
        }
    }
}