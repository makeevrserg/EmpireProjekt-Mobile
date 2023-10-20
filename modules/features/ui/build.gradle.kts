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
                // Compose MPP
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(compose.runtime)
                implementation(compose.uiTooling)
                implementation(compose.preview)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                // klibs
                implementation(libs.klibs.mikro.core)
                implementation(libs.klibs.mikro.platform)
                implementation(libs.klibs.kstorage)
                implementation(libs.klibs.kdi)
                // Moko
                implementation(libs.moko.resources.core)
                // Decompose
                implementation(libs.decompose.core)
                implementation(libs.decompose.compose.jetbrains)
                // Local
                implementation(projects.modules.services.resources)
                implementation(projects.modules.services.coreUi)
                implementation(projects.modules.services.buildKonfig)
                implementation(projects.modules.features.splash)
                implementation(projects.modules.features.root)
                implementation(projects.modules.features.theme)
            }
        }
        val androidMain by getting {
            dependencies {
                // Accompanist
                implementation(libs.google.accompanist.systemuicontroller)
                // Image loading
                implementation("io.coil-kt:coil:2.4.0")
                implementation("io.coil-kt:coil-compose:2.4.0")
            }
        }
    }
}
android {
    namespace = "${projectInfo.group}.features.ui.splash"
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlin.compilerExtensionVersion.get()
    }
    buildFeatures {
        compose = true
    }
}
