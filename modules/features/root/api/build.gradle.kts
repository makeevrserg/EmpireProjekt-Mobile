@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.property.extension.ModelPropertyValueExt.requireProjectInfo

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.core")
    id("ru.astrainteractive.gradleplugin.android.core")
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    android()
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Decompose
                implementation(libs.decompose.core)
            }
        }
    }
}
android {
    apply(plugin = "kotlin-parcelize")
    namespace = "${requireProjectInfo.group}.features.root.api"
}
