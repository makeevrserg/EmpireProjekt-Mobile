import ru.astrainteractive.gradleplugin.util.ProjectProperties.projectInfo

plugins {
    id("dev.icerock.mobile.multiplatform-resources")
    id("com.android.library")
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.core")
    id("ru.astrainteractive.gradleplugin.android.core")
}
kotlin {
    android()
    ios()
    iosSimulatorArm64()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.moko.resources.core)
            }
        }
        val iosX64Main by getting {
            resources.srcDirs("build/generated/moko/iosX64Main/src")
        }
        val iosArm64Main by getting {
            resources.srcDirs("build/generated/moko/iosArm64Main/src")
        }
        val iosSimulatorArm64Main by getting {
            resources.srcDirs("build/generated/moko/iosSimulatorArm64Main/src")
        }
    }
}
multiplatformResources {
    multiplatformResourcesPackage = "${projectInfo.group}.resources"
}
android {
    namespace = "${projectInfo.group}.resources"
    dependencies {
        implementation("com.google.android.material:material:1.9.0")
    }
    sourceSets {
        getByName("main").java.srcDirs("build/generated/moko/androidMain/src")
    }
}