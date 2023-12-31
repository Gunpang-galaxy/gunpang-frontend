plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    // firebase
    id("com.google.gms.google-services")

}

android {
    namespace = "com.gunpang.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.gunpang.app"
        minSdk = 30
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // multi-module
    // implementation(project(path=":common"))

    implementation(project(path=":common"))
    implementation(project(path=":app-ui"))
    implementation(project(path=":domain"))
    implementation(project(path=":data"))
    // android
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // jetpack compose
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("com.google.android.gms:play-services-games-v2:19.0.0")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // wearable
    implementation("androidx.wear:wear-remote-interactions:1.0.0")
    implementation ("com.google.android.gms:play-services-wearable:18.1.0")

    // health connect
    implementation ("androidx.health.connect:connect-client:1.1.0-alpha06")
    //implementation ("androidx.health.connect.client:1.0.0-alpha11")
    // samsung health (연동 X)
    // implementation ("com.samsung.android.sdk.healthdata:1.5.0")

    // test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // firebase
    implementation(platform("com.google.firebase:firebase-bom:32.4.0"))
    implementation ("com.google.firebase:firebase-messaging-ktx")
    implementation ("com.google.firebase:firebase-analytics-ktx")

    // google login
    implementation ("com.google.android.gms:play-services-auth:20.7.0")

    // The compose calendar library
    implementation ("com.kizitonwose.calendar:compose:2.4.0")
}

// firebase
apply(plugin = "com.google.gms.google-services")