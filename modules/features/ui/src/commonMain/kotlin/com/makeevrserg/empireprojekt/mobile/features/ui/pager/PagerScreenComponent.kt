package com.makeevrserg.empireprojekt.mobile.features.ui.pager

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.makeevrserg.empireprojekt.mobile.features.root.modal.RootBottomSheetComponent
import com.makeevrserg.empireprojekt.mobile.features.root.pager.PagerComponent
import com.makeevrserg.empireprojekt.mobile.features.root.screen.RootScreenComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.map.AndroidMapView
import com.makeevrserg.empireprojekt.mobile.features.ui.pager.components.PagerBottomBar
import com.makeevrserg.empireprojekt.mobile.features.ui.rating.users.RatingUsersScreenComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.status.StatusScreen
import com.makeevrserg.empireprojekt.mobile.features.ui.towny.towns.TownsScreenComponent

@Composable
fun PagerScreenComponent(
    pagerComponent: PagerComponent,
    rootScreenComponent: RootScreenComponent,
    rootBottomSheetComponent: RootBottomSheetComponent,
    modifier: Modifier = Modifier
) {
    val selectedChild by pagerComponent.selectedChild.subscribeAsState()
    val selectedIndex by pagerComponent.selectedIndex.subscribeAsState()
    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        bottomBar = {
            PagerBottomBar(
                selectedIndex = selectedIndex,
                onClicked = pagerComponent::select
            )
        }
    ) { paddingValues ->
        Crossfade(
            modifier = modifier
                .fillMaxWidth()
                .padding(paddingValues),
            targetState = selectedChild,
            label = "Crossfade instance composable"
        ) { instance ->
            when (instance) {
                is PagerComponent.Child.RatingUsers -> RatingUsersScreenComponent(
                    ratingUsersComponent = instance.ratingUsersComponent,
                    popComponent = rootScreenComponent
                )

                is PagerComponent.Child.Status -> StatusScreen(
                    themeSwitcherComponent = instance.themeSwitcherComponent,
                    rootStatusComponent = instance.rootStatusComponent,
                    rootBottomSheetComponent = rootBottomSheetComponent
                )

                is PagerComponent.Child.Towns -> TownsScreenComponent(
                    popComponent = rootScreenComponent,
                    townsComponent = instance.townsComponent
                )

                PagerComponent.Child.Map -> AndroidMapView()
            }
        }
//        Children(
//            stack = childStack,
//            modifier = modifier
//                .fillMaxSize()
//                .padding(paddingValues),
//            animation = stackAnimation(fade())
//        ) { child ->
//            when (val instance = child.instance) {
//                is PagerComponent.Child.RatingUsers -> RatingUsersScreenComponent(
//                    ratingUsersComponent = instance.ratingUsersComponent,
//                    popComponent = rootScreenComponent
//                )
//
//                is PagerComponent.Child.Status -> StatusScreen(
//                    themeSwitcherComponent = instance.themeSwitcherComponent,
//                    rootStatusComponent = instance.rootStatusComponent,
//                    rootBottomSheetComponent = rootBottomSheetComponent
//                )
//
//                is PagerComponent.Child.Towns -> TownsScreenComponent(
//                    popComponent = rootScreenComponent,
//                    townsComponent = instance.townsComponent
//                )
//
//                PagerComponent.Child.Map -> AndroidMapView()
//            }
//        }
    }
}
