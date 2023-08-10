@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.util.GradleProperty.Companion.gradleProperty
import ru.astrainteractive.gradleplugin.util.ProjectProperties.projectInfo

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("ru.astrainteractive.gradleplugin.java.core")
    id("ru.astrainteractive.gradleplugin.android.core")
    id("com.github.gmazzo.buildconfig")
    id("dev.icerock.mobile.multiplatform-resources")
    alias(libs.plugins.kotlin.serialization)
}

buildConfig {
    className("BuildKonfig") // forces the class name. Defaults to 'BuildConfig'
    packageName("${projectInfo.group}.shared") // forces the package. Defaults to '${project.group}'
    buildConfigField(
        "String",
        "VERSION_CODE",
        "\"${gradleProperty("project.version.code").integer}\""
    )
    buildConfigField("String", "VERSION_NAME", "\"${projectInfo.versionString}\"")
}

kotlin {
    android()
    ios()
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
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Settings
                implementation(libs.mppsettings)
                // klibs
                implementation(libs.klibs.mikro.core)
                implementation(libs.klibs.mikro.platform)
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
    implementation(libs.google.auth)
    implementation(libs.kotlin.coroutines.playServices)
}

multiplatformResources {
    multiplatformResourcesPackage = "mock"
}
