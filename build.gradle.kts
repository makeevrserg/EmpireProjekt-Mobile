// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    dependencies {
        classpath(libs.google.firebase.crsahlytics.gradle)
        classpath(libs.google.gms.services.gradle)
        classpath(libs.moko.resources.generator)
        classpath(libs.moko.network.generator)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.gradle.buildconfig) apply false
    alias(libs.plugins.gradle.ksp) apply false
    alias(libs.plugins.gradle.dokka) apply true
    alias(libs.plugins.gradle.shadow) apply true
    id("dokka-root")
    id("detekt-convention")
}

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
