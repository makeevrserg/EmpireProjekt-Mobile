pluginManagement {
    includeBuild("build-logic")
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
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "EmpireProjekt-Mobile"
// Services
include(":androidApp")
include(":modules:services:resources")
include(":modules:services:core-ui")
// Feature
include(":modules:features:root")
include(":modules:features:splash")
include(":modules:features:ui")
