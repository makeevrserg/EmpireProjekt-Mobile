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
                // Settings
                implementation(libs.mppsettings)
                // Ktor
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.contentNegitiation)
                implementation(libs.ktor.client.serialization)
                implementation(libs.ktor.client.json)
                implementation(libs.ktor.serialization.json)
                implementation(libs.ktor.logging)
                // Decompose
                implementation(libs.decompose.core)
                // Serialization
                implementation(libs.kotlin.serialization.json)
                // klibs
                implementation(libs.klibs.mikro.core)
                implementation(libs.klibs.mikro.platform)
                implementation(libs.klibs.kdi)
                // Coroutines
                implementation(libs.kotlin.coroutines.core)
            }
        }
    }
}
android {
    namespace = "${requireProjectInfo.group}.core"
}
