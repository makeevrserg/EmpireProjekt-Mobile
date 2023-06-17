import org.jetbrains.dokka.gradle.DokkaMultiModuleTask
import org.jetbrains.dokka.gradle.DokkaTask

group = libs.versions.project.group.get()
version = libs.versions.project.version.string.get()

plugins {
    id("org.jetbrains.dokka")
}

tasks.withType<DokkaMultiModuleTask> {
    includes.from("README.md")
    moduleName.set("EmpireProjekt-Mobile")
}

tasks.withType<DokkaTask>().configureEach {
    dokkaSourceSets.configureEach {
        perPackageOption {
            reportUndocumented.set(false)
        }
    }
}
