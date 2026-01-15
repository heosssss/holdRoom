plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.heosssss.core_ui"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

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
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    // 1. 제트팩 컴포즈 (BOM & Bundle)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.libraries)

    // 2. AndroidX 핵심
    implementation(libs.androidx.core.ktx)

    // 3. 테스트 및 디버그
    // 공통 컴포넌트의 Preview를 확인하기 위해 필요합니다.
    testImplementation(libs.bundles.test.libraries)
    debugImplementation(libs.androidx.compose.ui.tooling)
}