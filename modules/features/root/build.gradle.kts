@file:Suppress("UnusedPrivateMember")

import com.makeevrserg.empireprojekt.mobile.GradleProject.APPLICATION_ID
import com.makeevrserg.empireprojekt.mobile.GradleProject.VERSION_CODE
import com.makeevrserg.empireprojekt.mobile.GradleProject.VERSION_STRING

plugins {
    id("mpp-library-convention")
    id("com.github.gmazzo.buildconfig")
    kotlin("native.cocoapods")
    id("dev.icerock.mobile.multiplatform-resources")
}

buildConfig {
    className("BuildKonfig") // forces the class name. Defaults to 'BuildConfig'
    packageName(APPLICATION_ID + ".shared") // forces the package. Defaults to '${project.group}'
    buildConfigField("String", "VERSION_CODE", "\"${VERSION_CODE}\"")
    buildConfigField("String", "VERSION_NAME", "\"${VERSION_STRING}\"")
}

kotlin {

    cocoapods {
        summary = "EmpireProjektMobiel Root"
        homepage = libs.versions.cocoapods.homepage.get()
        version = libs.versions.project.version.string.get()
        ios.deploymentTarget = libs.versions.cocoapods.deployment.target.get()
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
            export(libs.mobileX.core.ktx)
            export(libs.moko.mvvm.core)
            export(libs.moko.mvvm.flow)
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Settings
                implementation(libs.mppsettings)
                // MobileX
                implementation(libs.mobileX.serviceLocator)
                api(libs.mobileX.core.ktx)
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
    }
}
android {
    apply(plugin = "kotlin-parcelize")
    namespace = APPLICATION_ID + ".shared"
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
