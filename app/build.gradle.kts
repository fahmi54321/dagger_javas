plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.dagger_java"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.dagger_java"
        minSdk = 24
        targetSdk = 35
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // swiperefreshlayout
    implementation(libs.swiperefreshlayout)

    implementation (libs.dagger)
    annotationProcessor(libs.dagger.compiler)

    implementation (libs.glide)

//    debugImplementation(libs.leakcanary.android)

    // ViewModel
    implementation (libs.lifecycle.viewmodel)
}