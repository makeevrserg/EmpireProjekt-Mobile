@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.util.ProjectProperties.projectInfo

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
                // klibs
                implementation(libs.klibs.mikro.core)
                implementation(libs.klibs.mikro.platform)
                implementation(libs.klibs.kstorage)
                implementation(libs.klibs.kdi)
                // Decompose
                implementation(libs.decompose.core)
                implementation(libs.essenty)
                // Moko
                implementation(libs.moko.mvvm.core)
                implementation(libs.moko.mvvm.flow)
                implementation(libs.moko.resources.core)
                // Coroutines
                implementation(libs.kotlin.coroutines.core)
                // MVIKotlin
                implementation(libs.mvikotlin)
                // Local
                implementation(projects.modules.services.coreResources)
                implementation(projects.modules.services.core)
                implementation(projects.modules.services.apiEmpireapi)
                implementation(projects.modules.features.root.api)
                implementation(projects.modules.features.splash.impl)
                implementation(projects.modules.features.rating.impl)
                implementation(projects.modules.features.theme.api)
                implementation(projects.modules.features.theme.impl)
                implementation(projects.modules.features.status.impl)
                implementation(projects.modules.features.towns.impl)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.room.ktx)
                implementation(libs.ktor.client.cio)
            }
        }
    }
}
android {
    apply(plugin = "kotlin-parcelize")
    namespace = "${projectInfo.group}.shared"
    sourceSets {
        getByName("main").java.srcDirs("build/generated/moko/androidMain/src")
    }
}

dependencies {
    // FireBase
    implementation(platform(libs.google.firebase.bom))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-common-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-messaging-ktx")
    implementation(libs.klibs.kstorage)
    implementation(libs.google.auth)
    implementation(libs.kotlin.coroutines.playServices)
    implementation("io.ktor:ktor-client-logging-jvm:2.3.2")
}
