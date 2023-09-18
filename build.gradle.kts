plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.dokka)
}

subprojects {
    apply(plugin = "org.jetbrains.dokka")

}

tasks.dokkaHtml {
    outputDirectory = layout.buildDirectory.dir("docs/html")
}

tasks.dokkaHtmlMultiModule {
    outputDirectory = layout.buildDirectory.dir("docs/htmlMultiModule")
}

tasks.dokkaGfm {
    outputDirectory = layout.buildDirectory.dir("docs/markdown")
}

dependencies {
    dokkaPlugin("org.jetbrains.dokka:android-documentation-plugin:${libs.versions.dokka.get()}")
}