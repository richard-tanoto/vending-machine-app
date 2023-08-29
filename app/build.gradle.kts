plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
}

android {
    namespace = "com.richard.vendingmachineapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.richard.vendingmachineapp"
        minSdk = 24
        targetSdk = 33
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
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.appCompat)
    implementation(libs.google.material)
    implementation(libs.androidx.constraintLayout)
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.androidTest.junit)
    androidTestImplementation(libs.androidTest.espresso)

    //Koin
    implementation(libs.koin.core)
    implementation(libs.koin.android)

    //Glide
    implementation(libs.glide.core)
    annotationProcessor(libs.glide.compiler)

    //Retrofit and Okhttp3
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter)
    implementation(libs.okhttp3.interceptor)

    //Room
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)

    //ViewModel and LiveData
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)
}