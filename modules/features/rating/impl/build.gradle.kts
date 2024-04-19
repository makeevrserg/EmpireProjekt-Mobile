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
                implementation(libs.klibs.mikro.extensions)
                implementation(libs.klibs.mikro.platform)
                implementation(libs.klibs.kstorage)
                implementation(libs.klibs.kdi)
                // settings
                implementation(libs.mppsettings)
                // Decompose
                implementation(libs.decompose.core)
                implementation(libs.essenty)
                // Moko
                implementation(libs.moko.resources.core)
                // Paging
                implementation(libs.klibs.paging)
                // Coroutines
                implementation(libs.kotlin.coroutines.core)
                // MVIKotlin
                implementation(libs.mvikotlin)
                implementation(libs.mvikotlin.main)
                implementation(libs.mvikotlin.coroutines)
                // Local
                implementation(projects.modules.services.coreResources)
                implementation(projects.modules.services.core)
                implementation(projects.modules.services.apiEmpireapi)
            }
        }
    }
}
android {
    namespace = "${requireProjectInfo.group}.rating"
}
