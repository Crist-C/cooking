buildscript {
    dependencies {
        // Firebase
        classpath(libs.google.services)
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false

    // KSP
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false

    // Hilt
    id(id = "com.google.dagger.hilt.android") version "2.44" apply false

    // Maps API_KEY
    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false

}
true // Needed to make the Suppress annotation work for the plugins block