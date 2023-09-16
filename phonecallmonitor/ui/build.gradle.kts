plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "pl.kamilszustak.callmonitor.ui.phonecallmonitor"

    compileSdk = 34
    defaultConfig {
        minSdk = 23
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(projects.phonecallmonitor.presentation)

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.androidx.lifecycle.compose)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android.compose)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.uiToolingPreview)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundationLayout)
    implementation(libs.androidx.compose.material3)
}