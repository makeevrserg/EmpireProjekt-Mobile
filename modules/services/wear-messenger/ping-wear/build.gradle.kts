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
    android()
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Kotlin
                implementation(libs.kotlin.serialization.json)
                // klibs
                implementation(libs.klibs.mikro.core)
                implementation(libs.klibs.mikro.platform)
                implementation(libs.klibs.mikro.extensions)
                implementation(libs.klibs.kstorage)
                implementation(libs.klibs.kdi)
                // horologist
                implementation(libs.google.horologist.datalayer)
                // Coroutines
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.kotlin.coroutines.playServices)
                // Local
                implementation(projects.modules.services.wearMessenger.api)
            }
        }
    }
}
android {
    namespace = "${requireProjectInfo.group}.wear.messenger.ping"
}
