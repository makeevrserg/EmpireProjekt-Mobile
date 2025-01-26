@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.property.extension.ModelPropertyValueExt.requireProjectInfo

plugins {
    id("org.jetbrains.compose")
    id("com.android.library")
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.core")
    id("ru.astrainteractive.gradleplugin.android.core")
    alias(libs.plugins.kotlin.compose.gradle)
}

kotlin {
    androidTarget()
    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Compose MPP
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(compose.runtime)
                implementation(compose.material)
                implementation(compose.materialIconsExtended)
                implementation(compose.uiTooling)
                implementation(compose.preview)
                // Moko
                implementation(libs.moko.resources.core)
                // Local
                implementation(projects.modules.services.core.resources)
                implementation(projects.modules.services.core.ui)
                implementation(projects.modules.services.core.nucleus)
                // Features
                implementation(projects.modules.features.root.api)
                implementation(projects.modules.features.towns.api)
                implementation(projects.modules.features.rating.api)
                implementation(projects.modules.features.status.api)
                implementation(projects.modules.features.theme.api)
                implementation(projects.modules.features.tabs.impl)
            }
        }
    }
}

android {
    namespace = "${requireProjectInfo.group}.features.tabs.ui"
}
