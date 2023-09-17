plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.com.android.library) apply false
    alias(libs.plugins.dokka)
}

subprojects {
    apply(plugin = "org.jetbrains.dokka")
}

dependencies {
    dokkaPlugin("org.jetbrains.dokka:android-documentation-plugin:${libs.versions.dokka.get()}")
}