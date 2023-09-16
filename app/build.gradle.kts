plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "pl.kamilszustak.callmonitor"
    compileSdk = 34

    defaultConfig {
        applicationId = "pl.kamilszustak.callmonitor"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(projects.logger)
    implementation(projects.phonecallmonitor.server)
    implementation(projects.phonecallmonitor.ui)
    implementation(projects.phonecallmonitor.presentation)
    implementation(projects.phonecallmonitor.domain)
    implementation(projects.phonecallmonitor.data)
    implementation(projects.phonecallmonitor.datasource)

    coreLibraryDesugaring(libs.desugarJdkLibs)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.dateTime)

    implementation(libs.androidx.appStartup)
    implementation(libs.androidx.lifecycle.compose)
    implementation(libs.androidx.lifecycle.viewModelCompose)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android.core)
    implementation(libs.koin.android.compose)
    implementation(libs.koin.ktor)

    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.cio)
    implementation(libs.ktor.server.contentNegotiation)
    implementation(libs.ktor.serialization)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.uiToolingPreview)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundationLayout)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.materialIconsExtended)
    implementation(libs.androidx.activity.compose)

    implementation(libs.logcat)
}