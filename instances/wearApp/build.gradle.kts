import ru.astrainteractive.gradleplugin.property.PropertyValue.Companion.baseGradleProperty
import ru.astrainteractive.gradleplugin.property.PropertyValue.Companion.secretProperty
import ru.astrainteractive.gradleplugin.property.extension.ModelPropertyValueExt.requireProjectInfo
import ru.astrainteractive.gradleplugin.property.extension.PrimitivePropertyValueExt.requireInt
import ru.astrainteractive.gradleplugin.property.extension.PrimitivePropertyValueExt.stringOrEmpty
import ru.astrainteractive.gradleplugin.util.Base64Util

plugins {
    kotlin("plugin.serialization")
    id("com.android.application")
    id("kotlin-android")
    id("ru.astrainteractive.gradleplugin.java.core")
    id("ru.astrainteractive.gradleplugin.android.core")
    alias(libs.plugins.kotlin.compose.gradle)
    id("ru.astrainteractive.gradleplugin.android.apk.name")
}

android {
    namespace = "${requireProjectInfo.group}"
    compileSdk = baseGradleProperty("android.sdk.compile").requireInt

    defaultConfig {
        applicationId = "${requireProjectInfo.group}"
        minSdk = 26
        targetSdk = baseGradleProperty("android.sdk.target").requireInt
        versionCode = baseGradleProperty("project.version.code").requireInt
        versionName = requireProjectInfo.versionString
        vectorDrawables {
            useSupportLibrary = true
        }
        setProperty(
            "archivesBaseName",
            "${requireProjectInfo.name}-wearos-${requireProjectInfo.versionString}"
        )
    }

    signingConfigs {
        val keyStoreFile = file("keystore.jks")
        val secretKeyAlias = secretProperty("KEY_ALIAS").stringOrEmpty
        val secretKeyPassword = secretProperty("KEY_PASSWORD").stringOrEmpty
        val secretStorePassword = secretProperty("STORE_PASSWORD").stringOrEmpty
        if (!keyStoreFile.exists()) {
            logger.warn("Keystore file not exists - creating")
            val base64String = secretProperty("KEYSTORE_BASE64").stringOrEmpty
            if (base64String.isNotBlank()) Base64Util.fromBase64(base64String, keyStoreFile)
        }
        if (!keyStoreFile.exists()) {
            logger.error("Keystore file could not be created")
        }
        getByName("debug") {
            if (keyStoreFile.exists()) {
                keyAlias = secretKeyAlias
                keyPassword = secretKeyPassword
                storePassword = secretStorePassword
                storeFile = keyStoreFile
            }
        }
        create("release") {
            if (keyStoreFile.exists()) {
                keyAlias = secretKeyAlias
                keyPassword = secretKeyPassword
                storePassword = secretStorePassword
                storeFile = keyStoreFile
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
    implementation(libs.androidx.compose.wear.material)
    implementation(libs.androidx.compose.wear.foundation)

    implementation(libs.wear.glance.tiles)

    implementation(libs.wear.tiles)
    implementation(libs.wear.tiles.material)
    implementation(libs.google.horologist.compose.tools)
    implementation(libs.google.horologist.tiles)
    implementation(libs.wear.complications.datasource.ktx)
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
    implementation(libs.decompose.compose)
    implementation(libs.google.gms.services.wearable)
    // Local
    implementation(projects.modules.features.root.api)
    implementation(projects.modules.features.root.impl)
    implementation(projects.modules.features.theme.api)
    implementation(projects.modules.features.theme.impl)
    implementation(projects.modules.features.theme.ui)
    implementation(projects.modules.features.status.api)
    implementation(projects.modules.features.status.impl)
    implementation(projects.modules.services.core.ui)
    implementation(projects.modules.services.core.resources)
    implementation(projects.modules.services.wearMessenger.api)
    implementation(projects.modules.services.wearMessenger.pingWear)
    implementation(projects.modules.services.wearMessenger.common)
    implementation(projects.modules.services.core.nucleus)
}
