import ru.astrainteractive.gradleplugin.util.GradleProperty.Companion.gradleProperty
import ru.astrainteractive.gradleplugin.util.ProjectProperties.projectInfo
import ru.astrainteractive.gradleplugin.util.SecretProperty.Companion.secretProperty

plugins {
    kotlin("plugin.serialization")
    id("com.android.application")
    id("kotlin-android")
    id("ru.astrainteractive.gradleplugin.java.core")
    id("ru.astrainteractive.gradleplugin.android.core")
    id("ru.astrainteractive.gradleplugin.android.apk.name")
}

android {
    namespace = "${projectInfo.group}"
    apply(plugin = "kotlin-parcelize")
    if (file("google-services.json").exists()) {
        apply(plugin = "com.google.gms.google-services")
        apply(plugin = "com.google.firebase.crashlytics")
    }
    defaultConfig {
        applicationId = projectInfo.group
        versionCode = gradleProperty("project.version.code").integer
        versionName = projectInfo.versionString
    }
    defaultConfig {
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        val secretKeyAlias = secretProperty("KEY_ALIAS").string
        val secretKeyPassword = secretProperty("KEY_PASSWORD").string
        val secretStorePassword = secretProperty("STORE_PASSWORD").string
        getByName("debug") {
            if (file("keystore.jks").exists()) {
                keyAlias = secretKeyAlias
                keyPassword = secretKeyPassword
                storePassword = secretStorePassword
                storeFile = file("keystore.jks")
            }
        }
        create("release") {
            if (file("keystore.jks").exists()) {
                keyAlias = secretKeyAlias
                keyPassword = secretKeyPassword
                storePassword = secretStorePassword
                storeFile = file("keystore.jks")
            }
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlin.compilerExtensionVersion.get()
    }
    packagingOptions {
        with(resources.excludes) {
            add("META-INF/*.kotlin_module")
            add("META-INF/AL2.0")
            add("META-INF/LGPL2.1")
        }
    }
    buildTypes {
//        applicationVariants.all(
//            com.makeevrserg.empireprojekt.mobile.ApplicationVariantAction(
//                project
//            )
//        )
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
