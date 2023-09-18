import org.jetbrains.dokka.DokkaConfiguration.Visibility
import org.jetbrains.dokka.base.DokkaBase
import org.jetbrains.dokka.base.DokkaBaseConfiguration
import org.jetbrains.dokka.gradle.DokkaTaskPartial

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.dokka)
}

subprojects {
    apply(plugin = "org.jetbrains.dokka")

    tasks.withType<DokkaTaskPartial>().configureEach {
        dokkaSourceSets.configureEach {
            documentedVisibilities = Visibility.values()
                .toSet()

            val readmeFileExists = file("README.md")
                .exists()
            if (readmeFileExists) {
                includes.from("README.md")
            }
        }
    }
}

buildscript {
    dependencies {
        classpath(libs.dokka.base)
    }
}

tasks.dokkaHtmlMultiModule {
    outputDirectory = layout.projectDirectory.dir("docs")
    includes.from("README.md")

    pluginConfiguration<DokkaBase, DokkaBaseConfiguration> {
        customAssets = layout.projectDirectory.file("images")
            .asFile
            .takeIf { it.exists() && it.isDirectory }
            ?.walk()
            ?.filter { it.isFile }
            ?.toList()
            ?: DokkaBaseConfiguration.defaultCustomAssets
    }
}

dependencies {
    dokkaPlugin("org.jetbrains.dokka:android-documentation-plugin:${libs.versions.dokka.get()}")
}