package com.makeevrserg.empireprojekt.mobile.features.root.di

import com.makeevrserg.empireprojekt.mobile.api.empireapi.di.ApiEmpireApiModule
import com.makeevrserg.empireprojekt.mobile.features.rating.user.di.RatingUserModule
import com.makeevrserg.empireprojekt.mobile.features.rating.users.di.RatingUsersModule
import com.makeevrserg.empireprojekt.mobile.features.root.pager.di.PagerModule
import com.makeevrserg.empireprojekt.mobile.features.splash.di.SplashComponentModule
import com.makeevrserg.empireprojekt.mobile.features.status.di.StatusModule
import com.makeevrserg.empireprojekt.mobile.features.theme.di.ThemeSwitcherModule
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.di.TownsModule
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule

interface RootModule {

    val coreModule: CoreModule
    val statusModule: StatusModule
    val themeSwitcherModule: ThemeSwitcherModule
    val splashModule: SplashComponentModule
    val ratingUserModule: RatingUserModule
    val ratingUsersModule: RatingUsersModule
    val townsModule: TownsModule
    val pagerModule: PagerModule
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
            ThemeSwitcherModule.Default(coreModule = coreModule)
        }

        override val statusModule: StatusModule by lazy {
            StatusModule.Default(
                coreModule = coreModule
            )
        }

        override val splashModule: SplashComponentModule by lazy {
            SplashComponentModule.Default(
                coreModule = coreModule
            )
        }

        override val townsModule: TownsModule by lazy {
            TownsModule.Default(
                apiEmpireApiModule = apiEmpireApiModule,
                coreModule = coreModule
            )
        }

        override val ratingUserModule: RatingUserModule by lazy {
            RatingUserModule.Default(
                apiEmpireApiModule = apiEmpireApiModule,
                coreModule = coreModule
            )
        }

        override val ratingUsersModule: RatingUsersModule by lazy {
            RatingUsersModule.Default(
                apiEmpireApiModule = apiEmpireApiModule,
                coreModule = coreModule

            )
        }

        override val pagerModule: PagerModule by lazy {
            PagerModule.Default(this)
        }
    }
}
