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
                implementation(compose.material)
                implementation(compose.materialIconsExtended)
                // MobileX
                implementation(libs.mobileX.paging)
                implementation(libs.mobileX.core.ktx)
                implementation(libs.mobileX.serviceLocator)
                // Moko
                implementation(libs.moko.resources.core)
                // Decompose
                implementation(libs.decompose.core)
                implementation(libs.decompose.compose.jetbrains)
                // Local
                implementation(projects.modules.features.splash)
                implementation(projects.modules.services.resources)
                implementation(projects.modules.services.coreUi)
                implementation(projects.modules.features.root)
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
