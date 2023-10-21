@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.util.ProjectProperties.projectInfo

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.core")
    id("ru.astrainteractive.gradleplugin.android.core")
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    android()
    ios()
    iosSimulatorArm64()
    sourceSets {
        val commonMain by getting {
            dependencies {
                // kotlin
                implementation(libs.kotlin.serialization.json)
                // klibs
                implementation(libs.klibs.mikro.core)
                implementation(libs.klibs.kdi)
                // Decompose
                implementation(libs.decompose.core)
                // Coroutines
                implementation(libs.kotlin.coroutines.core)
                // Moko
                implementation(libs.moko.resources.core)
                // Ktor
                implementation(libs.ktor.client.core)
                // Local
                implementation(projects.modules.services.core)
            }
        }
    }
}
android {
    namespace = "${projectInfo.group}.status"
}