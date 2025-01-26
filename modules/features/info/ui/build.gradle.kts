@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.property.extension.ModelPropertyValueExt.requireProjectInfo

plugins {
    id("org.jetbrains.compose")
    id("com.android.library")
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.core")
    id("ru.astrainteractive.gradleplugin.android.core")
    alias(libs.plugins.kotlin.compose.gradle)
    id("dev.icerock.mobile.multiplatform-resources")
}

kotlin {
    androidTarget()
    applyDefaultHierarchyTemplate()
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
                implementation(compose.materialIconsExtended)
                // Moko
                implementation(libs.moko.resources.core)
                // Local
                implementation(projects.modules.services.core.nucleus)
                implementation(projects.modules.services.core.resources)
                implementation(projects.modules.services.core.ui)
                implementation(projects.modules.services.buildKonfig)
            }
        }
    }
}

android {
    namespace = "${requireProjectInfo.group}.features.info.ui"
}

multiplatformResources {
    resourcesPackage.set("${requireProjectInfo.group}.features.info.ui")
    resourcesClassName.set("IR")
}
