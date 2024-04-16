@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.property.extension.ModelPropertyValueExt.requireProjectInfo

plugins {
    id("org.jetbrains.compose")
    id("com.android.library")
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.core")
    id("ru.astrainteractive.gradleplugin.android.core")
    id("ru.astrainteractive.gradleplugin.android.compose")
}

kotlin {
    android()
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Compose MPP
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(compose.runtime)
                implementation(compose.uiTooling)
                implementation(compose.preview)
                implementation(compose.material)
                implementation(compose.materialIconsExtended)
                // Decompose
                implementation(libs.decompose.core)
                implementation(libs.decompose.compose.jetbrains)
                // Local
                implementation(projects.modules.services.coreResources)
                implementation(projects.modules.services.buildKonfig)
                implementation(projects.modules.services.coreUi)
                implementation(projects.modules.services.core)
                implementation(projects.modules.features.root.api)
                implementation(projects.modules.features.root.impl)
                implementation(projects.modules.features.rating.impl)
                implementation(projects.modules.features.rating.ui)
                implementation(projects.modules.features.splash.impl)
                implementation(projects.modules.features.splash.ui)
                implementation(projects.modules.features.status.impl)
                implementation(projects.modules.features.status.ui)
                implementation(projects.modules.features.theme.api)
                implementation(projects.modules.features.theme.impl)
                implementation(projects.modules.features.theme.ui)
                implementation(projects.modules.features.towns.impl)
                implementation(projects.modules.features.towns.ui)
                implementation(projects.modules.features.info.ui)
                implementation(projects.modules.features.map.ui)
            }
        }
        val androidMain by getting {
            dependencies {
                // Accompanist
//                implementation(libs.google.accompanist.systemuicontroller)
                // Image loading
//                implementation("io.coil-kt:coil:2.4.0")
//                implementation("io.coil-kt:coil-compose:2.4.0")
            }
        }
    }
}

android {
    namespace = "${requireProjectInfo.group}.features.root.ui"
}
