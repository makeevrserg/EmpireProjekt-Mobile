import ru.astrainteractive.gradleplugin.util.GradleProperty.Companion.gradleProperty
import ru.astrainteractive.gradleplugin.util.ProjectProperties.jinfo
import ru.astrainteractive.gradleplugin.util.ProjectProperties.projectInfo
import ru.astrainteractive.gradleplugin.util.SecretProperty.Companion.secretProperty

plugins {
    kotlin("plugin.serialization")
    id("com.android.application")
    id("kotlin-android")
    id("ru.astrainteractive.gradleplugin.java.core")
    id("ru.astrainteractive.gradleplugin.android.compose")
    id("ru.astrainteractive.gradleplugin.android.apk.name")
}

android {
    namespace = "${projectInfo.group}"
    compileSdk = gradleProperty("android.sdk.compile").integer

    defaultConfig {
        applicationId = "${projectInfo.group}"
        minSdk = 26
        targetSdk = gradleProperty("android.sdk.target").integer
        versionCode = gradleProperty("project.version.code").integer
        versionName = projectInfo.versionString
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileOptions {
        sourceCompatibility = jinfo.jsource
        targetCompatibility = jinfo.jtarget
    }
    kotlinOptions {
        jvmTarget = jinfo.jtarget.majorVersion
    }
    signingConfigs {
        val secretKeyAlias = runCatching { secretProperty("KEY_ALIAS").string }.getOrNull() ?: ""
        val secretKeyPassword =
            runCatching { secretProperty("KEY_PASSWORD").string }.getOrNull() ?: ""
        val secretStorePassword =
            runCatching { secretProperty("STORE_PASSWORD").string }.getOrNull() ?: ""
        getByName("debug") {
            if (file("../androidApp/keystore.jks").exists()) {
                keyAlias = secretKeyAlias
                keyPassword = secretKeyPassword
                storePassword = secretStorePassword
                storeFile = file("../androidApp/keystore.jks")
            }
        }
        create("release") {
            if (file("../androidApp/keystore.jks").exists()) {
                keyAlias = secretKeyAlias
                keyPassword = secretKeyPassword
                storePassword = secretStorePassword
                storeFile = file("../androidApp/keystore.jks")
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Kotlin
    implementation(libs.kotlin.serialization.json)
    // klibs
    implementation(libs.klibs.mikro.extensions)
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
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.wear.compose:compose-navigation:1.2.0")

    implementation("androidx.glance:glance-wear-tiles:1.0.0-alpha05")

    implementation("androidx.wear.tiles:tiles:1.2.0")
    implementation("androidx.wear.tiles:tiles-material:1.2.0")
    implementation("com.google.android.horologist:horologist-compose-tools:0.5.3")
    implementation("com.google.android.horologist:horologist-tiles:0.5.3")
    implementation("androidx.wear.watchface:watchface-complications-data-source-ktx:1.1.1")
    implementation("com.google.android.horologist:horologist-datalayer-watch:0.5.3")
    implementation("com.google.android.horologist:horologist-datalayer-phone:0.5.3")
    // klibs
    implementation(libs.klibs.mikro.core)
    implementation(libs.klibs.mikro.platform)
    implementation(libs.klibs.kstorage)
    implementation(libs.klibs.kdi)
    // Settings
    implementation(libs.mppsettings)
    // moko
    implementation(libs.moko.resources.core)
    // Decompose
    implementation(libs.decompose.core)
    implementation(libs.decompose.compose.jetpack)
    implementation(libs.decompose.android)
    implementation("com.google.android.gms:play-services-wearable:18.0.0")
    // Local
    implementation(projects.modules.features.root.impl)
    implementation(projects.modules.features.theme.api)
    implementation(projects.modules.features.theme.impl)
    implementation(projects.modules.features.theme.ui)
    implementation(projects.modules.features.status.impl)
    implementation(projects.modules.services.coreUi)
    implementation(projects.modules.services.coreResources)
    implementation(projects.modules.services.wearMessenger)
    implementation(projects.modules.services.core)
}
