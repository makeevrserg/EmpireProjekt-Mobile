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
    useKotlinOutput { internalVisibility = false }
    buildConfigField(
        name = "VERSION_CODE",
        value = "${baseGradleProperty("project.version.code").requireInt}"
    )
    buildConfigField(
        name = "VERSION_NAME",
        value = "${requireProjectInfo.versionString}"
    )
    buildConfigField(
        name = "PROD_URL",
        value = "https://empireapi.astrainteractive.ru"
    )
}
kotlin {
    androidTarget()
    applyDefaultHierarchyTemplate()
}

android {
    namespace = "${requireProjectInfo.group}.buildkonfig"
}
