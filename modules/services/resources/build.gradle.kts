plugins {
    id("mpp-library-convention")
    id("dev.icerock.mobile.multiplatform-resources")
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.moko.resources.core)
            }
        }
    }
}
multiplatformResources {
    multiplatformResourcesPackage = "${libs.versions.project.group.get()}.resources"
}
android {
    namespace = "${libs.versions.project.group.get()}.resources"
    dependencies {
        implementation("com.google.android.material:material:1.9.0")
    }
}