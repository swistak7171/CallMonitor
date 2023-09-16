plugins {
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
}

dependencies {
    implementation(projects.phonecallmonitor.domain)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.dateTime)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
}