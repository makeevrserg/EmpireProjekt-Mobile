@file:Suppress("UnusedPrivateMember")

import com.makeevrserg.empireprojekt.mobile.GradleProject.ACTIVITY_AD_UNIT_ID
import com.makeevrserg.empireprojekt.mobile.GradleProject.BANNER_AD_UNIT_ID
import com.makeevrserg.empireprojekt.mobile.GradleProject.INTERSITIAL_AD_UNIT_ID
import com.makeevrserg.empireprojekt.mobile.GradleProject.WEB_CLIENT_ID

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
    defaultConfig {
        buildConfigField("String", "WEB_CLIENT_ID", WEB_CLIENT_ID)
        buildConfigField("String", "BANNER_AD_UNIT_ID", BANNER_AD_UNIT_ID)
        buildConfigField("String", "ACTIVITY_AD_UNIT_ID", ACTIVITY_AD_UNIT_ID)

        buildConfigField("String", "INTERSITIAL_AD_UNIT_ID", INTERSITIAL_AD_UNIT_ID)
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlin.compilerExtensionVersion.get()
    }
    buildFeatures {
        compose = true
    }
}
