@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.property.extension.ModelPropertyValueExt.requireProjectInfo

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.core")
    id("ru.astrainteractive.gradleplugin.android.core")
}

kotlin {
    androidTarget()
    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Coroutines
                implementation(libs.kotlin.coroutines.core)
                // klibs
                implementation(libs.klibs.mikro.core)
                implementation(libs.klibs.mikro.platform)
                implementation(libs.klibs.kstorage)
                implementation(libs.klibs.kdi)
                // settings
                implementation(libs.mppsettings)
                // Decompose
                implementation(libs.decompose.core)
                // Local
                implementation(projects.modules.services.core.nucleus)
                implementation(projects.modules.features.root.api)
                implementation(projects.modules.features.towns.api)
                implementation(projects.modules.features.rating.api)
                implementation(projects.modules.features.status.api)
                implementation(projects.modules.features.theme.api)
            }
        }
    }
}

android {
    namespace = "${requireProjectInfo.group}.features.tabs"
}
