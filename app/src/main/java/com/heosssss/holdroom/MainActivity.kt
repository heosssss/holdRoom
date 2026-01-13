package com.heosssss.holdroom

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.heosssss.core_ui.theme.HoldRoomTheme
import com.heosssss.holdroom.ui.AppRootScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() { // 앱이 실행될 때 맨 처음 실행되는 화면/Jetpack Compose를 쓰기 좋은 Activity 클래스 상속받음
    override fun onCreate(savedInstanceState: Bundle?) { // 상속받은 onCreate 재정의 / savedInstanceState : 저장해둔 상태가 들어옴
        installSplashScreen() // 스플래시
        super.onCreate(savedInstanceState) // 부모클래스 먼저 호출 (중요)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                Color.TRANSPARENT,  // 라이트 테마 scrim
                Color.TRANSPARENT   // 다크 테마 scrim
            ),
            navigationBarStyle = SystemBarStyle.auto(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            )
        )
        setContent {
            HoldRoomTheme {
//               Surface(color = MaterialTheme.colorScheme.background) { // material3 디자인의 "표면" 개념을 표현하는 컴포저블
                   AppRootScreen()
//               }
            }
        }
    }
}
