@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.util.ProjectProperties.projectInfo

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
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                // klibs
                implementation(libs.klibs.mikro.extensions)
                // Kotlin
                implementation(libs.kotlin.datetime)
                // Moko
                implementation(libs.moko.resources.core)
                // Local
                implementation(projects.modules.services.coreResources)
                implementation(projects.modules.services.coreUi)
                implementation(projects.modules.features.towns.impl)
            }
        }
    }
}

android {
    namespace = "${projectInfo.group}.features.towns.ui"
}
