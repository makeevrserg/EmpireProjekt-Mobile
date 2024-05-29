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
                implementation(compose.uiTooling)
                implementation(compose.preview)
                implementation(compose.material)
                implementation(compose.materialIconsExtended)
                // Moko
                implementation(libs.moko.resources.core)
                // Local
                implementation(projects.modules.services.coreResources)
                implementation(projects.modules.services.coreUi)
                implementation(projects.modules.services.buildKonfig)
                implementation(projects.modules.features.status.impl)
                implementation(projects.modules.features.root.api)
                implementation(projects.modules.features.theme.api)
            }
        }
    }
}

android {
    namespace = "${requireProjectInfo.group}.features.status.ui"
}
