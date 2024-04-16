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
        setProperty("archivesBaseName", "${projectInfo.name}-${projectInfo.versionString}")
    }
    defaultConfig {
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        val secretKeyAlias = runCatching {
            secretProperty("KEY_ALIAS").string
        }.getOrNull() ?: ""
        val secretKeyPassword = runCatching {
            secretProperty("KEY_PASSWORD").string
        }.getOrNull() ?: ""
        val secretStorePassword = runCatching {
            secretProperty("STORE_PASSWORD").string
        }.getOrNull() ?: ""
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
    lint {
        abortOnError = false
    }
}

dependencies {
    // Kotlin
    implementation(libs.kotlin.serialization.json)
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
    // FireBase
    implementation(platform(libs.google.firebase.bom))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-messaging-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation(libs.google.auth)
    implementation(libs.kotlin.coroutines.playServices)
    implementation(libs.google.firebase.crsahlytics.ktx)
    debugImplementation(libs.leakcanary)
    // klibs
    implementation(libs.klibs.mikro.core)
    implementation(libs.klibs.mikro.platform)
    implementation(libs.klibs.kstorage)
    implementation(libs.klibs.kdi)
    // moko
    implementation(libs.moko.resources.core)
    // Decompose
    implementation(libs.decompose.core)
    implementation(libs.decompose.compose.jetpack)
    implementation(libs.decompose.android)
    implementation("com.google.android.gms:play-services-wearable:18.0.0")
    // wear
    implementation(libs.google.horologist.datalayer)
    // work
    implementation("androidx.work:work-runtime:2.8.0")
    implementation("androidx.work:work-runtime-ktx:2.8.0")
    // Local
    implementation(projects.modules.features.root)
    implementation(projects.modules.features.theme)
    implementation(projects.modules.features.status)
    implementation(projects.modules.features.ui)
    implementation(projects.modules.services.coreUi)
    implementation(projects.modules.services.resources)
    implementation(projects.modules.services.wearMessenger.api)
    implementation(projects.modules.services.wearMessenger.pingWear)
}
