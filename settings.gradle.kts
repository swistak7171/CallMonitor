import java.nio.file.FileSystems

rootProject.name = "CallMonitor"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

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