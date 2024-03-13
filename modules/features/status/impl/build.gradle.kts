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
                // MviKotlin
                implementation(libs.mvikotlin)
                implementation(libs.mvikotlin.main)
                implementation(libs.mvikotlin.coroutines)
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

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.tests.assertk)
                implementation(libs.tests.turbine)
                implementation(libs.kotlin.coroutines.test)
            }
        }
    }
}
android {
    namespace = "${projectInfo.group}.status"
}
