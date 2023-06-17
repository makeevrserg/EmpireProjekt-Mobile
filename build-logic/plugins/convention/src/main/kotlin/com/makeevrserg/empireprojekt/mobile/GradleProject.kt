package com.makeevrserg.empireprojekt.mobile

import libs
import org.gradle.api.Project
import java.io.InputStream
import java.util.Properties

object GradleProject {
    val Project.APPLICATION_ID: String
        get() = libs.versions.project.group.get()

    val Project.MIN_SDK_VERSION: Int
        get() = libs.versions.project.sdk.min.get().toInt()

    val Project.TARGET_SDK_VERSION: Int
        get() = libs.versions.project.sdk.target.get().toInt()

    val Project.COMPILE_SDK_VERSION: Int
        get() = libs.versions.project.sdk.compile.get().toInt()

    val Project.VERSION_CODE: Int
        get() = libs.versions.project.version.code.get().toInt()

    val Project.VERSION_STRING: String
        get() = libs.versions.project.version.string.get()

    val Project.KEY_ALIAS: String
        get() = getCredential(this, "KEY_ALIAS")
    val Project.KEY_PASSWORD: String
        get() = getCredential(this, "KEY_PASSWORD")
    val Project.STORE_PASSWORD: String
        get() = getCredential(this, "STORE_PASSWORD")

    val Project.WEB_CLIENT_ID: String
        get() = getCredential(this, "WEB_CLIENT_ID")
    val Project.BANNER_AD_UNIT_ID: String
        get() = getCredential(this, "BANNER_AD_UNIT_ID")
    val Project.ACTIVITY_AD_UNIT_ID: String
        get() = getCredential(this, "ACTIVITY_AD_UNIT_ID")
    val Project.INTERSITIAL_AD_UNIT_ID: String
        get() = getCredential(this, "INTERSITIAL_AD_UNIT_ID")

    private fun getCredential(project: Project, path: String): String {
        val properties: Properties = Properties()
        val localProperties = project.rootProject.file("local.properties")
        if (!localProperties.exists()) return "NO_LOCAL_PROPERTIES"
        val inputStream: InputStream = localProperties.inputStream()
        properties.load(inputStream)
        return properties.getProperty(path)
    }
}
