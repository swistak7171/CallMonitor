plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(projects.phonecallmonitor.domain)
    implementation(projects.logger)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.dateTime)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)

    testImplementation(libs.junit)
    testImplementation(libs.mockk.core)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}