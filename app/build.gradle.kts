plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.heosssss.holdroom"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.heosssss.holdroom"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    buildFeatures {
        compose = true
    }
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    // 1. 내부 모듈 (Internal Modules)
    implementation(project(":feature"))
    implementation(project(":core-ui"))
    implementation(project(":data"))

    // 2. 제트팩 컴포즈 (BOM & Bundle)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.libraries)

    // 3. AndroidX 핵심 라이브러리
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation("androidx.core:core-splashscreen:1.0.1")

    // 4. 의존성 주입 (Hilt)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // 5. 직렬화 (Serialization)
    implementation(libs.kotlinx.serialization.json)

    // 6. 테스트 관련
    testImplementation(libs.bundles.test.libraries)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.bundles.android.test.libraries)

    // 7. 디버그 도구 (개발용 툴, 배포판에는 포함 안 됨)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}