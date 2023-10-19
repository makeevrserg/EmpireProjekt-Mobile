package com.makeevrserg.empireprojekt.mobile.wear.features.root

import androidx.navigation.NavHostController

class NavHostRootComponent(val navController: NavHostController) : RootComponent {

    override fun openStatuses() {
        navController.navigate(RootComponent.Child.Statuses::class.simpleName!!)
    }
}
