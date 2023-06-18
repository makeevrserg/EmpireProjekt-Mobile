
import com.makeevrserg.empireprojekt.mobile.GradleProject.APPLICATION_ID
import com.makeevrserg.empireprojekt.mobile.GradleProject.VERSION_CODE
import com.makeevrserg.empireprojekt.mobile.GradleProject.VERSION_STRING

plugins {
    kotlin("plugin.serialization")
    id("android-app-convention")
    id("signing-convention")
}

android {
    namespace = "${libs.versions.project.group.get()}"
    apply(plugin = "kotlin-parcelize")
    if (file("google-services.json").exists()) {
        apply(plugin = "com.google.gms.google-services")
        apply(plugin = "com.google.firebase.crashlytics")
    }
    defaultConfig {
        applicationId = APPLICATION_ID
        versionCode = VERSION_CODE
        versionName = VERSION_STRING
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
        debug {
            isDebuggable = true
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlin.compilerExtensionVersion.get()
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // Coroutines
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.coroutines.android)
    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material:material")
    // Accompanist
    implementation(libs.google.accompanist.systemuicontroller)
    // FireBase
    implementation(platform(libs.google.firebase.bom))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-messaging-ktx")
    implementation(libs.google.auth)
    implementation(libs.google.firebase.analytics)
    implementation(libs.kotlin.coroutines.playServices)
    implementation(libs.google.firebase.crsahlytics.ktx)
    debugImplementation(libs.leakcanary)
    // MobileX
    implementation(libs.mobileX.core.ktx)
    implementation(libs.mobileX.serviceLocator)
    // Decompose
    implementation(libs.decompose.core)
    implementation(libs.decompose.compose.jetpack)
    implementation(libs.decompose.android)
    // Local
    implementation(projects.modules.features.root)
    implementation(projects.modules.features.ui)
    implementation(projects.modules.services.coreUi)
    implementation(projects.modules.services.resources)
}
