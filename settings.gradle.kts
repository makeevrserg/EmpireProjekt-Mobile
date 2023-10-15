pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        mavenLocal()
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://maven.pkg.jetbrains.space/public/p/ktor/eap")
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "EmpireProjekt-Mobile"
// Services
include(":androidApp")
include(":wearApp")
include(":modules:services:resources")
include(":modules:services:core-ui")
include(":modules:services:core")
include(":modules:services:wear-messenger")
include(":modules:services:build-konfig")
// Feature
include(":modules:features:root")
include(":modules:features:splash")
include(":modules:features:ui")
