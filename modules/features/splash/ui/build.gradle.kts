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
                // Moko
                implementation(libs.moko.resources.core)
                // Local
                implementation(projects.modules.services.core.resources)
                implementation(projects.modules.services.core.nucleus)
                implementation(projects.modules.services.core.ui)
                implementation(projects.modules.features.splash.impl)
                implementation(projects.modules.features.root.api)
            }
        }
    }
}

android {
    namespace = "${requireProjectInfo.group}.features.splash.ui"
}
