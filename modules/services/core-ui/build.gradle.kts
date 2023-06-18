@file:Suppress("UnusedPrivateMember")

plugins {
    id("mpp-compose-convention")
    id("org.jetbrains.compose")
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                // MobileX
                implementation(libs.mobileX.paging)
                implementation(libs.mobileX.core.ktx)
                // Decompose
                implementation(libs.decompose.core)
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
    namespace = "${libs.versions.project.group.get()}.core.ui"
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlin.compilerExtensionVersion.get()
    }
    buildFeatures {
        compose = true
    }
}
