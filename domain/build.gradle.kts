plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}
kotlin {
    // 코틀린 컴파일러 타겟 설정
    jvmToolchain(21)
}

dependencies{
    // 1. 의존성 주입 (Hilt가 내부적으로 사용하는 javax.inject)
    implementation("javax.inject:javax.inject:1")

    // 2. 코루틴 (Domain에서 UseCase가 Flow나 suspend를 반환한다면 필수)
    implementation(libs.kotlinx.coroutines.core)

    // 3. 테스트
    testImplementation(libs.bundles.test.libraries)

}
