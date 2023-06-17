import com.makeevrserg.empireprojekt.mobile.GradleProject.COMPILE_SDK_VERSION
import com.makeevrserg.empireprojekt.mobile.GradleProject.MIN_SDK_VERSION
import com.makeevrserg.empireprojekt.mobile.GradleProject.TARGET_SDK_VERSION

plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = COMPILE_SDK_VERSION
    defaultConfig {
        minSdk = MIN_SDK_VERSION
        targetSdk = TARGET_SDK_VERSION
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    packagingOptions {
        with(resources.excludes) {
            add("META-INF/*.kotlin_module")
            add("META-INF/AL2.0")
            add("META-INF/LGPL2.1")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.majorVersion
    }
}
