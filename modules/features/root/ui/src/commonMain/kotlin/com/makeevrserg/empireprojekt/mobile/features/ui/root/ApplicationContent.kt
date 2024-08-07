package com.makeevrserg.empireprojekt.mobile.features.ui.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.makeevrserg.empireprojekt.mobile.features.root.RootComponent
import com.makeevrserg.empireprojekt.mobile.features.root.screen.DefaultRootScreenComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.map.AndroidMapView
import com.makeevrserg.empireprojekt.mobile.features.ui.pager.PagerScreenComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.rating.user.RatingUserScreenComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.rating.users.RatingUsersScreenComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.splash.SplashScreenComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.status.StatusScreen
import com.makeevrserg.empireprojekt.mobile.features.ui.towny.towns.TownsScreenComponent

@Composable
fun ApplicationContent(
    rootComponent: RootComponent,
    modifier: Modifier = Modifier
) {
    val childStack by rootComponent.rootScreenComponent.childStack.subscribeAsState()
    Children(
        stack = childStack,
        modifier = modifier.fillMaxSize(),
        animation = stackAnimation(slide())
    ) { configuration ->

        when (val screen = configuration.instance) {
            is DefaultRootScreenComponent.Configuration.Splash -> SplashScreenComponent(
                rootRouter = rootComponent.rootScreenComponent,
                splashComponent = screen.splashComponent
            )

            is DefaultRootScreenComponent.Configuration.RatingUser -> RatingUserScreenComponent(
                ratingUserComponent = screen.ratingUserComponent,
                popComponent = rootComponent.rootScreenComponent
            )

            is DefaultRootScreenComponent.Configuration.Pager -> PagerScreenComponent(
                pagerComponent = screen.pagerComponent,
                ratingUsersScreen = { modifier, child ->
                    RatingUsersScreenComponent(
                        ratingUsersComponent = child.ratingUsersComponent,
                        popComponent = rootComponent.rootScreenComponent
                    )
                },
                townsScreen = { modifier, child ->
                    TownsScreenComponent(
                        popComponent = rootComponent.rootScreenComponent,
                        townsComponent = child.townsComponent
                    )
                },
                statusScreen = { modifier, child ->
                    StatusScreen(
                        themeSwitcherComponent = child.themeSwitcherComponent,
                        rootStatusComponent = child.rootStatusComponent,
                        rootBottomSheetRouter = rootComponent.rootBottomSheetComponent
                    )
                },
                mapScreen = { modifier, child ->
                    AndroidMapView()
                }
            )
        }
    }
}
