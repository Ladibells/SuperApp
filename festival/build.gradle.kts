plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android.plugin)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "dev.ladibells.festival"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 27

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
        debug {
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":utilities"))
    implementation(project(":design"))
    implementation(project(":datasource"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android)
    ksp(libs.androidx.hilt.compiler)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation(libs.navigation.compose)
    implementation(libs.lottie)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp3)
    implementation(libs.okhttp3.logging.interceptor)

    implementation(libs.androidx.compose.runtime)
}