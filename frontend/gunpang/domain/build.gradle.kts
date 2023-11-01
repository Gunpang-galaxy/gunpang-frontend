plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.gunpang.domain"
    compileSdk = 34

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    //multi-module
    implementation(project(path=":common"))
    implementation(project(path=":data"))

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    //implementation("com.google.android.material:material:1.10.0")

    // compose runtime
    implementation("androidx.compose.runtime:runtime:1.5.4")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.4")
    implementation("androidx.compose.runtime:runtime-rxjava2:1.5.4")

    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services")
    // viewModelScope
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx")
    //원격 함수 조정
    implementation("androidx.wear:wear-remote-interactions:1.0.0")

    // CapabilityClient
    implementation("com.google.android.gms:play-services-wearable:18.1.0")
    implementation("androidx.wear:wear-phone-interactions:1.0.1")
    // RemoteActivityHelper

    // google login
    implementation ("com.google.android.gms:play-services-auth:20.7.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}