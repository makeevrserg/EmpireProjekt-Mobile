buildscript {
    dependencies {
        classpath(libs.google.firebase.crsahlytics.gradle)
        classpath(libs.google.gms.services.gradle)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.compose.gradle) apply false
    alias(libs.plugins.gradle.buildconfig) apply false
    alias(libs.plugins.moko.resources) apply false
    // klibs - core
    alias(libs.plugins.klibs.gradle.detekt) apply false
    alias(libs.plugins.klibs.gradle.detekt.compose) apply false
    alias(libs.plugins.klibs.gradle.dokka.root) apply false
    alias(libs.plugins.klibs.gradle.dokka.module) apply false
    alias(libs.plugins.klibs.gradle.java.core) apply false
    alias(libs.plugins.klibs.gradle.publication) apply false
    alias(libs.plugins.klibs.gradle.rootinfo) apply false
    // klibs - android
    alias(libs.plugins.klibs.gradle.android.core) apply false
    alias(libs.plugins.klibs.gradle.android.compose) apply false
    alias(libs.plugins.klibs.gradle.android.apk.sign) apply false
    alias(libs.plugins.klibs.gradle.android.apk.name) apply false
}

apply(plugin = "ru.astrainteractive.gradleplugin.detekt")

/**
 * This function will delete every ./build folder
 * ./gradlew :cleanProject
 */
tasks.register("cleanProject", Delete::class) {
    fun clearProject(project: Project) {
        project.childProjects.values.forEach(::clearProject)
        delete(project.buildDir)
    }
    clearProject(rootProject)
}
