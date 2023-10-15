@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.util.ProjectProperties.projectInfo

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("ru.astrainteractive.gradleplugin.java.core")
    id("ru.astrainteractive.gradleplugin.android.core")
    id("dev.icerock.mobile.multiplatform-resources")
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    android()
    ios()
    iosSimulatorArm64()
    cocoapods {
        summary = projectInfo.description
        homepage = projectInfo.url
        version = projectInfo.versionString
        ios.deploymentTarget = "16.0"
        podfile = project.file("../../../iosApp/Podfile")
        framework {
            baseName = "Root"
            isStatic = false
            export(libs.moko.resources.core)
            export(projects.modules.services.resources)
            export(projects.modules.features.splash)
            export(projects.modules.services.core)
            export(libs.decompose.core)
            export(libs.essenty)
            export(libs.moko.mvvm.core)
            export(libs.moko.mvvm.flow)
            export(libs.klibs.mikro.platform)
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Settings
                implementation(libs.mppsettings)
                // klibs
                implementation(libs.klibs.mikro.core)
                api(libs.klibs.mikro.platform)
                implementation(libs.klibs.kstorage)
                implementation(libs.klibs.kdi)
                // Decompose
                api(libs.decompose.core)
                api(libs.essenty)
                // Ktor
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.contentNegitiation)
                implementation(libs.ktor.client.serialization)
                implementation(libs.ktor.client.json)
                implementation(libs.ktor.serialization.json)
                // Moko
                api(libs.moko.mvvm.core)
                api(libs.moko.mvvm.flow)
                implementation(libs.moko.resources.core)
                // Serialization
                implementation(libs.kotlin.serialization.json)
                // Coroutines
                implementation(libs.kotlin.coroutines.core)
                // MVIKotlin
                implementation(libs.mvikotlin)
                // Local
                api(projects.modules.services.resources)
                api(projects.modules.features.splash)
                api(projects.modules.services.core)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.room.ktx)
                implementation(libs.ktor.client.cio)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
        val iosX64Main by getting {
            resources.srcDirs("build/generated/moko/iosX64Main/src")
        }
        val iosArm64Main by getting {
            resources.srcDirs("build/generated/moko/iosArm64Main/src")
        }
        val iosSimulatorArm64Main by getting {
            this.dependsOn(iosMain)
            resources.srcDirs("build/generated/moko/iosSimulatorArm64Main/src")
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
}

multiplatformResources {
    multiplatformResourcesPackage = "mock"
}
