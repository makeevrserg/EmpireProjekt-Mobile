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
// Instances
include(":instances:androidApp")
include(":instances:wearApp")
// Services
include(":modules:services:core-resources")
include(":modules:services:core-ui")
include(":modules:services:core")
include(":modules:services:wear-messenger")
include(":modules:services:build-konfig")
include(":modules:services:api-empireapi")
// Feature
include(":modules:features:root:api")
include(":modules:features:root:impl")
include(":modules:features:root:ui")
include(":modules:features:splash:impl")
include(":modules:features:splash:ui")
include(":modules:features:theme:api")
include(":modules:features:theme:impl")
include(":modules:features:theme:ui")
include(":modules:features:status:impl")
include(":modules:features:status:ui")
include(":modules:features:rating:impl")
include(":modules:features:rating:ui")
include(":modules:features:towns:impl")
include(":modules:features:towns:ui")
include(":modules:features:info:ui")
include(":modules:features:map:ui")
