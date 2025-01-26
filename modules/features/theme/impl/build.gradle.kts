@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.property.extension.ModelPropertyValueExt.requireProjectInfo

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.core")
    id("ru.astrainteractive.gradleplugin.android.core")
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidTarget()
    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                // klibs
                implementation(libs.klibs.mikro.core)
                implementation(libs.klibs.kstorage)
                implementation(libs.klibs.kdi)
                // Settings
                implementation(libs.mppsettings)
                // Decompose
                implementation(libs.decompose.core)
                // Coroutines
                implementation(libs.kotlin.coroutines.core)
                // Local
                implementation(projects.modules.features.theme.api)
                implementation(projects.modules.services.core.nucleus)
            }
        }
    }
}
android {
    namespace = "${requireProjectInfo.group}.theme.impl"
}
