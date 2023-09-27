@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)

    // KSP
    id("com.google.devtools.ksp")

    // Hilt
    id ("kotlin-kapt")
    id("com.google.dagger.hilt.android")

    // Google Services
    id("com.google.gms.google-services")
}

android {
    namespace = "com.ccastro.cooking"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ccastro.cooking"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    // vvv-->> Solucionana el conflicto kotlin-kapt
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    // ^^^-->> Solucionana el conflicto kotlin-kapt
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/gradle/incremental.annotation.processors"
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    // Kotlin Runtime
    implementation (libs.core.ktx)
    implementation (libs.lifecycle.runtime.ktx)

    // DAGGER HILT
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    // Hilt Navigation
    implementation (libs.androidx.hilt.navigation.compose)

    // Navigation Compose
    implementation(libs.androidx.navigation.compose)

    // Pager
    implementation(libs.accompanist.pager)

    // Coil : descargar y visualizar imagenes desde una url
    implementation(libs.coil.compose)
    implementation(libs.coil.gif)

    // Utils (Lerp)
    implementation(libs.androidx.ui.util)

    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.adapter.rxjava)

    // GSon
    implementation (libs.gson)

    // Room
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.room.compiler)
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.room.compiler)
    // optional - Test helpers
    testImplementation(libs.androidx.room.testing)
    // optional - Paging 3 Integration
    implementation(libs.androidx.room.paging)

}