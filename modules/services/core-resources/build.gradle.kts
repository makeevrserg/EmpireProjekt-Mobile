@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.property.extension.ModelPropertyValueExt.requireProjectInfo

plugins {
    id("dev.icerock.mobile.multiplatform-resources")
    id("com.android.library")
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.core")
    id("ru.astrainteractive.gradleplugin.android.core")
}

kotlin {
    androidTarget()
    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.moko.resources.core)
            }
        }
    }
}

multiplatformResources {
    resourcesPackage.set("${requireProjectInfo.group}.core.resources")
}

android {
    namespace = "${requireProjectInfo.group}.core.resources"
    dependencies {
        implementation("com.google.android.material:material:1.12.0")
    }
    sourceSets {
        getByName("main").java.srcDirs("build/generated/moko/androidMain/src")
    }
}
