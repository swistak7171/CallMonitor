plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "pl.kamilszustak.callmonitor.datasource.phonecallmonitor"

    compileSdk = 34
    defaultConfig {
        minSdk = 23
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(projects.phonecallmonitor.data)
    implementation(projects.logger)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.dateTime)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)

    implementation(libs.androidx.core)

    testImplementation(libs.junit)
    testImplementation(libs.mockk.core)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
    testImplementation(libs.androidx.test.runner)
    testImplementation(libs.androidx.test.ext.junit)
    testImplementation(libs.robolectric)
}