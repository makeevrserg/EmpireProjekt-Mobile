import ru.astrainteractive.gradleplugin.property.PropertyValue.Companion.baseGradleProperty
import ru.astrainteractive.gradleplugin.property.PropertyValue.Companion.secretProperty
import ru.astrainteractive.gradleplugin.property.extension.ModelPropertyValueExt.requireProjectInfo
import ru.astrainteractive.gradleplugin.property.extension.PrimitivePropertyValueExt.requireInt
import ru.astrainteractive.gradleplugin.property.extension.PrimitivePropertyValueExt.stringOrEmpty

plugins {
    kotlin("plugin.serialization")
    id("com.android.application")
    id("kotlin-android")
    id("ru.astrainteractive.gradleplugin.java.core")
    id("ru.astrainteractive.gradleplugin.android.compose")
    id("ru.astrainteractive.gradleplugin.android.apk.name")
}

android {
    namespace = "${requireProjectInfo.group}"
    compileSdk = baseGradleProperty("android.sdk.compile").requireInt
    apply(plugin = "kotlin-parcelize")

    defaultConfig {
        applicationId = "${requireProjectInfo.group}"
        minSdk = 26
        targetSdk = baseGradleProperty("android.sdk.target").requireInt
        versionCode = baseGradleProperty("project.version.code").requireInt
        versionName = requireProjectInfo.versionString
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    signingConfigs {
        val secretKeyAlias = runCatching { secretProperty("KEY_ALIAS").stringOrEmpty }.getOrNull() ?: ""
        val secretKeyPassword =
            runCatching { secretProperty("KEY_PASSWORD").stringOrEmpty }.getOrNull() ?: ""
        val secretStorePassword =
            runCatching { secretProperty("STORE_PASSWORD").stringOrEmpty }.getOrNull() ?: ""
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
    implementation(libs.google.horologist.datalayer.watch)
    implementation(libs.google.horologist.datalayer.phone)
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
    implementation(projects.modules.services.wearMessenger.api)
    implementation(projects.modules.services.wearMessenger.pingWear)
    implementation(projects.modules.services.core)
}
