plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.citation"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.citation"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        compilerOptions {
            jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
        }
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.navigation.compose)
    implementation(project(":core:common"))
    implementation(project(":core:di"))
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":feature:details:presentation"))
    implementation(project(":feature:watchlist:presentation"))
    implementation(project(":feature:home:presentation"))
    implementation(project(":feature:explore:presentation"))
    implementation(project(":feature:explore:data"))
    implementation(project(":feature:details:data"))
    implementation(project(":feature:watchlist:data"))
    implementation(project(":feature:home:data"))
    implementation(project(":feature:explore:domain"))
    implementation(project(":feature:details:domain"))
    implementation(project(":feature:watchlist:domain"))
    implementation(project(":feature:home:domain"))
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
    implementation(libs.retrofit)
    implementation(libs.moshi.kotlin)
}