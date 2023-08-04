@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.util.ProjectProperties.projectInfo

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.core")
    id("ru.astrainteractive.gradleplugin.android.core")
}

kotlin {
    android()
    ios()
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Coroutines
                implementation(libs.kotlin.coroutines.core)
                // MobileX
                implementation(libs.mobileX.core.ktx)
                implementation(libs.mobileX.serviceLocator)
                // Moko
                implementation(libs.moko.mvvm.core)
                implementation(libs.moko.mvvm.flow)
                // Decompose
                implementation(libs.decompose.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.tests.assertk)
                implementation(libs.tests.turbine)
            }
        }
    }
}

android {
    namespace = "${projectInfo.group}.features.logic.splash"
}
