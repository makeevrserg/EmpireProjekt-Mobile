package com.makeevrserg.empireprojekt.mobile.features.root.di

import com.makeevrserg.empireprojekt.mobile.api.empireapi.di.ApiEmpireApiModule
import com.makeevrserg.empireprojekt.mobile.features.rating.user.di.RatingUserModule
import com.makeevrserg.empireprojekt.mobile.features.rating.user.di.RatingUserModuleImpl
import com.makeevrserg.empireprojekt.mobile.features.rating.users.di.RatingUsersModule
import com.makeevrserg.empireprojekt.mobile.features.rating.users.di.RatingUsersModuleImpl
import com.makeevrserg.empireprojekt.mobile.features.splash.di.SplashComponentModule
import com.makeevrserg.empireprojekt.mobile.features.status.di.StatusModule
import com.makeevrserg.empireprojekt.mobile.features.status.di.StatusModuleImpl
import com.makeevrserg.empireprojekt.mobile.features.theme.di.ThemeSwitcherModule
import com.makeevrserg.empireprojekt.mobile.features.theme.di.ThemeSwitcherModuleImpl
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.di.TownsModule
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.di.TownsModuleImpl
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule

interface RootModule {

    val coreModule: CoreModule
    val statusModule: StatusModule
    val themeSwitcherModule: ThemeSwitcherModule
    val splashModule: SplashComponentModule
    val ratingUserModule: RatingUserModule
    val ratingUsersModule: RatingUsersModule
    val townsModule: TownsModule
    val pagerModule: com.makeevrserg.empireprojekt.mobile.features.root.pager.di.PagerModule
    val apiEmpireApiModule: ApiEmpireApiModule

    class Default : RootModule {

        override val coreModule: CoreModule by lazy {
            CoreModule.Default()
        }

        override val apiEmpireApiModule: ApiEmpireApiModule by lazy {
            ApiEmpireApiModule.Default(
                coreModule = coreModule
            )
        }
        override val themeSwitcherModule: ThemeSwitcherModule by lazy {
            ThemeSwitcherModuleImpl(coreModule = coreModule)
        }

        override val statusModule: StatusModule by lazy {
            StatusModuleImpl(
                coreModule = coreModule
            )
        }

        override val splashModule: SplashComponentModule by lazy {
            SplashComponentModule.Default(
                coreModule = coreModule
            )
        }

        override val townsModule: TownsModule by lazy {
            TownsModuleImpl(
                apiEmpireApiModule = apiEmpireApiModule,
                coreModule = coreModule
            )
        }

        override val ratingUserModule: RatingUserModule
            get() = RatingUserModuleImpl(
                apiEmpireApiModule = apiEmpireApiModule,
                coreModule = coreModule
            )

        override val ratingUsersModule: RatingUsersModule by lazy {
            RatingUsersModuleImpl(
                apiEmpireApiModule = apiEmpireApiModule,
                coreModule = coreModule

            )
        }

        override val pagerModule: com.makeevrserg.empireprojekt.mobile.features.root.pager.di.PagerModule by lazy {
            com.makeevrserg.empireprojekt.mobile.features.root.pager.di.PagerModule.Default(
                ratingUsersModule = ratingUsersModule,
                themeSwitcherModule = themeSwitcherModule,
                statusModule = statusModule,
                townsModule = townsModule,
                coreModule = coreModule
            )
        }
    }
}
