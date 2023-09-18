plugins {
    alias(libs.plugins.android.application)
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
            excludes += setOf(
                "/META-INF/{AL2.0,LGPL2.1}",
                "/META-INF/LICENSE.md",
                "/META-INF/LICENSE-notice.md"
            )
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

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.dateTime)

    implementation(libs.androidx.appStartup)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android.core)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.activity.compose)

    implementation(libs.logcat)

    testImplementation(libs.junit)
    testImplementation(libs.konsist)
    testImplementation(libs.koin.test.core)
    testImplementation(libs.kotlinx.serialization.json)
}