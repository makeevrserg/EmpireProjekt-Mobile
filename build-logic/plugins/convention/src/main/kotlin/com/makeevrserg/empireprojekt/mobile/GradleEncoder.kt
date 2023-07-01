package com.makeevrserg.empireprojekt.mobile

import com.makeevrserg.empireprojekt.mobile.GradleUtils.toKFile
import org.gradle.api.Project
import org.jetbrains.kotlin.konan.file.File
import org.jetbrains.kotlin.konan.properties.saveToFile
import java.util.Properties

object GradleEncoder {
    private const val GOOGLE_SERVICES_FILE_NAME = "google-services.json"
    private const val GOOGLE_SERVICES_KEY_NAME = "googleservices"
    private const val KEYSTORE_FILE_NAME = "keystore.jks"
    private const val KEYSTORE_KEY_NAME = "keystore.jks"
    private val Project.androidAppFolder: File
        get() = rootProject.projectDir
            .toKFile()
            .child("androidApp")
    private val Project.secretFolder: File
        get() = rootProject.projectDir
            .toKFile()
            .child(".secret")
    private val Project.envFile: File
        get() = secretFolder.child(".env")

    fun Project.encodeSecretFiles() {
        val googleServicesBase64 = secretFolder
            .child(GOOGLE_SERVICES_FILE_NAME)
            .let(GradleUtils::toBase64)

        val keystoreBase64 = secretFolder
            .child(KEYSTORE_FILE_NAME)
            .let(GradleUtils::toBase64)

        val properties = Properties().apply {
            setProperty(GOOGLE_SERVICES_KEY_NAME, googleServicesBase64)
            setProperty(KEYSTORE_KEY_NAME, keystoreBase64)
        }
        rootProject.envFile.apply {
            delete()
            createNew()
        }.also(properties::saveToFile)
    }

    fun Project.decodeSecretFiles() {
        val properties = Properties()
        properties.load(envFile.bufferedReader())
        val googleServicesBase64 = System.getenv(GOOGLE_SERVICES_KEY_NAME)
            ?: properties.getProperty(GOOGLE_SERVICES_KEY_NAME)
        val keystoreBase64 = System.getenv(KEYSTORE_KEY_NAME)
            ?: properties.getProperty(KEYSTORE_KEY_NAME)

        androidAppFolder.child(GOOGLE_SERVICES_FILE_NAME).let {
            if (it.exists) return
            it.parentFile.mkdirs()
            it.createNew()
            GradleUtils.fromBase64(googleServicesBase64, it)
        }
        androidAppFolder.child(KEYSTORE_FILE_NAME).let {
            if (it.exists) return
            it.parentFile.mkdirs()
            it.createNew()
            GradleUtils.fromBase64(keystoreBase64, it)
        }
    }
}