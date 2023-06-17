@file:Suppress("UnusedPrivateMember")

import com.makeevrserg.empireprojekt.mobile.GradleProject.APPLICATION_ID

plugins {
    id("org.jetbrains.compose")
    id("mpp-compose-convention")
}

kotlin {
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
    namespace = APPLICATION_ID + ".features.ui.splash"
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlin.compilerExtensionVersion.get()
    }
    buildFeatures {
        compose = true
    }
}
