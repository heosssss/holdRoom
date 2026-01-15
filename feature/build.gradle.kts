plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.heosssss.feature"
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
    // 1. 내부 모듈 (Internal Modules)
    implementation(project(":core-ui"))
    implementation(project(":domain"))

    // 2. 제트팩 컴포즈 (BOM & Bundle)
    // bundle에 navigation, ui, foundation 등이 포함되어 있어 중복 코드를 제거했습니다.
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.libraries)

    // 3. AndroidX & 이미지 로딩
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.coil.compose)

    // 4. 의존성 주입 (Hilt)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // 5. 테스트 관련 (Bundle 활용)
    testImplementation(libs.bundles.test.libraries)
    androidTestImplementation(libs.bundles.android.test.libraries)
}