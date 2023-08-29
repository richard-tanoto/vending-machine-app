pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            //Built in
            library("androidx-core", "androidx.core:core-ktx:1.10.1")
            library("androidx-appCompat", "androidx.appcompat:appcompat:1.6.1")
            library("google-material", "com.google.android.material:material:1.9.0")
            library("androidx-constraintLayout", "androidx.constraintlayout:constraintlayout:2.1.4")
            library("test-junit", "junit:junit:4.13.2")
            library("androidTest-junit", "androidx.test.ext:junit:1.1.5")
            library("androidTest-espresso", "androidx.test.espresso:espresso-core:3.5.1")

            //Koin
            library("koin-core","io.insert-koin:koin-core:3.0.1")
            library("koin-android","io.insert-koin:koin-android:3.0.1")

            //Glide
            library("glide-core", "com.github.bumptech.glide:glide:4.13.2")
            library("glide-compiler", "com.github.bumptech.glide:compiler:4.13.2")

            //Retrofit2 and okhttp3
            library("retrofit-core","com.squareup.retrofit2:retrofit:2.9.0")
            library("retrofit-converter","com.squareup.retrofit2:converter-gson:2.9.0")
            library("okhttp3-interceptor","com.squareup.okhttp3:logging-interceptor:4.10.0")

            //Room
            library("room-runtime","androidx.room:room-runtime:2.5.2")
            library("room-compiler","androidx.room:room-compiler:2.5.2")
            library("room-ktx","androidx.room:room-ktx:2.5.2")

            //ViewModel and LiveData
            library("lifecycle-viewmodel","androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
            library("lifecycle-livedata","androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
        }
    }
}

rootProject.name = "VendingMachineApp"
include(":app")
