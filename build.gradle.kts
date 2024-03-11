buildscript {
    dependencies {
        classpath(libs.google.firebase.crsahlytics.gradle)
        classpath(libs.google.gms.services.gradle)
        classpath(libs.moko.resources.generator)
        classpath(libs.moko.network.generator)
        classpath("ru.astrainteractive.gradleplugin:convention:0.5.2")
        classpath("ru.astrainteractive.gradleplugin:android:0.5.2")
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
