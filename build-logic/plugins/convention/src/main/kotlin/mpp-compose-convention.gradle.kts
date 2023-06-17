@file:Suppress("UnusedPrivateMember")

import com.makeevrserg.empireprojekt.mobile.GradleProject.COMPILE_SDK_VERSION
import com.makeevrserg.empireprojekt.mobile.GradleProject.MIN_SDK_VERSION
import com.makeevrserg.empireprojekt.mobile.GradleProject.TARGET_SDK_VERSION
import org.gradle.kotlin.dsl.kotlin

plugins {
    id("com.android.library")
    kotlin("multiplatform")
}
java {
    java.sourceCompatibility = JavaVersion.VERSION_11
    java.targetCompatibility = JavaVersion.VERSION_11
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.majorVersion
}
kotlin {
    android()
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = JavaVersion.VERSION_11.majorVersion
        }
    }
    sourceSets {
        val commonMain by getting
        val androidMain by getting
        val jvmMain by getting
    }
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
}
