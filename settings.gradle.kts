import java.nio.file.FileSystems

include(":phonecallmonitor:data")


include(":phonecallmonitor:domain")


pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "CallMonitor"

rootDir.walk()
    .maxDepth(2)
    .filter { file ->
        rootDir != file &&
                file.isDirectory &&
                file.name != rootProject.name &&
                file.name != "buildSrc" &&
                (file("${file.absolutePath}/build.gradle.kts").exists() ||
                        file("${file.absolutePath}/build.gradle").exists())
    }
    .forEach { file ->
        val separator = FileSystems.getDefault().separator
        val modulePath = file.path.removePrefix(rootProject.projectDir.path)
        val moduleId = modulePath.replace(separator, ":")

        include(moduleId)
    }