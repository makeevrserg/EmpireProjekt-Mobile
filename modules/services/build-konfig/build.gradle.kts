@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.property.PropertyValue.Companion.baseGradleProperty
import ru.astrainteractive.gradleplugin.property.extension.ModelPropertyValueExt.requireProjectInfo
import ru.astrainteractive.gradleplugin.property.extension.PrimitivePropertyValueExt.requireInt

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.core")
    id("com.github.gmazzo.buildconfig")
    id("ru.astrainteractive.gradleplugin.android.core")
}

buildConfig {
    className("BuildKonfig") // forces the class name. Defaults to 'BuildConfig'
    packageName("${requireProjectInfo.group}.buildkonfig") // forces the package. Defaults to '${project.group}'
    buildConfigField(
        "String",
        "VERSION_CODE",
        "\"${baseGradleProperty("project.version.code").requireInt}\""
    )
    buildConfigField("String", "VERSION_NAME", "\"${requireProjectInfo.versionString}\"")
    buildConfigField("String", "PROD_URL", "\"https://empireapi.astrainteractive.ru\"")
}
kotlin {
    android()
}

android {
    namespace = "${requireProjectInfo.group}.buildkonfig"
}
