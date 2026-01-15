plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.heosssss.data"
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    // 1. 내부 모듈 (Data는 오직 Domain만 의존합니다)
    implementation(project(":domain"))

    // 2. AndroidX 핵심
    implementation(libs.androidx.core.ktx)

    // 3. 로컬 데이터베이스 (Room Bundle 활용)
    implementation(libs.bundles.room.libraries)
    ksp(libs.androidx.room.compiler)

    // 4. 의존성 주입 (Hilt)
    // data 모듈은 ViewModel이 없으므로 hilt-navigation-compose는 삭제해도 무방합니다.
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // 5. 비동기 및 직렬화
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)

    // 6. 테스트 관련 (Bundle 활용)
    testImplementation(libs.bundles.test.libraries)
    androidTestImplementation(libs.bundles.android.test.libraries)
}