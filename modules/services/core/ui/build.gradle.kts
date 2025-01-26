@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.property.extension.ModelPropertyValueExt.requireProjectInfo

plugins {
    id("org.jetbrains.compose")
    id("com.android.library")
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.core")
    id("ru.astrainteractive.gradleplugin.android.core")
    alias(libs.plugins.kotlin.compose.gradle)
}
kotlin {
    androidTarget()
    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                // klibs
                implementation(libs.klibs.mikro.core)
                implementation(libs.klibs.mikro.platform)
                implementation(libs.klibs.kstorage)
                implementation(libs.klibs.kdi)
                // Decompose
                implementation(libs.decompose.core)
                implementation(libs.decompose.compose)
                // Compose extensions
                implementation(libs.composeext.shimmer)
                // Moko
                implementation(libs.moko.resources.core)
                implementation(libs.moko.resources.compose)
                // Compose
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(compose.runtime)
                // Local
                implementation(projects.modules.services.core.resources)
                implementation(projects.modules.services.core.nucleus)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.google.accompanist.flowlayout)
                // Image loading
                implementation("io.coil-kt:coil-compose:2.7.0")
            }
        }
    }
}
android {
    namespace = "${requireProjectInfo.group}.core.ui"
}
