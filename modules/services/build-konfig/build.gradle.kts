@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.util.GradleProperty.Companion.gradleProperty
import ru.astrainteractive.gradleplugin.util.ProjectProperties.projectInfo

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.core")
    id("com.github.gmazzo.buildconfig")
    id("ru.astrainteractive.gradleplugin.android.core")
}

buildConfig {
    className("BuildKonfig") // forces the class name. Defaults to 'BuildConfig'
    packageName("${projectInfo.group}.buildkonfig") // forces the package. Defaults to '${project.group}'
    buildConfigField(
        "String",
        "VERSION_CODE",
        "\"${gradleProperty("project.version.code").integer}\""
    )
    buildConfigField("String", "VERSION_NAME", "\"${projectInfo.versionString}\"")
}

kotlin {
    android()
    ios()
    iosSimulatorArm64()
}

android {
    namespace = "${projectInfo.group}.buildkonfig"
}
