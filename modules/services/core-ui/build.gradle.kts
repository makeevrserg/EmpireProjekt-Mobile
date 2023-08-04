@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.util.ProjectProperties.projectInfo

plugins {
    id("org.jetbrains.compose")
    id("com.android.library")
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.core")
    id("ru.astrainteractive.gradleplugin.android.core")
}
kotlin {
    android()
    sourceSets {
        val commonMain by getting {
            dependencies {
                // MobileX
                implementation(libs.mobileX.paging)
                implementation(libs.mobileX.core.ktx)
                // Decompose
                implementation(libs.decompose.core)
                implementation(libs.decompose.compose.jetbrains)
                // Moko
                implementation(libs.moko.resources.core)
                implementation(libs.moko.resources.compose)
                // Compose
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(compose.material)
                implementation(compose.materialIconsExtended)
                implementation(compose.runtime)
                // Local
                implementation(project(":modules:services:resources"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.google.accompanist.flowlayout)
            }
        }
    }
}
android {
    namespace = "${projectInfo.group}.core.ui"
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlin.compilerExtensionVersion.get()
    }
    buildFeatures {
        compose = true
    }
}
