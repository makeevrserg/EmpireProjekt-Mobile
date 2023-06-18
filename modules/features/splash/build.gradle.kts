@file:Suppress("UnusedPrivateMember")

import com.makeevrserg.empireprojekt.mobile.GradleProject.APPLICATION_ID

plugins {
    id("mpp-library-convention")
}

kotlin {
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
    namespace = APPLICATION_ID + ".features.logic.splash"
}
