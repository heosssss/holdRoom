package com.heosssss.holdroom.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.heosssss.holdroom.navigation.AppNavHost
import com.heosssss.holdroom.navigation.BottomNavigationBar

@Composable
fun AppRootScreen() {
    val navController = rememberNavController() // 첫 컴포지션때 navController 생성하고 그 후에 recomposition 일어나도 기존 것을 기억해서 재사용함

    Scaffold( // 화면의 기본 뼈대 레이아웃을 만들어 주는 컴포저블
        modifier = Modifier.fillMaxSize(),
        contentColor = Color.Transparent,
        contentWindowInsets = WindowInsets(0),
        bottomBar = { BottomNavigationBar(navController = navController) }
    ){
        innerPadding ->
        AppNavHost( // 실제 화면 전환을 담당함
            navController = navController,
            modifier = Modifier
                .padding(innerPadding)
                .windowInsetsPadding(WindowInsets(0)), //todo. 이거 확인해보고 삭제해도될듯?
        )
    }
}





@Preview(
    showBackground = true,    // 뒷배경 흰색으로 깔아줌
    showSystemUi = true       // 상태바, 네비바까지 같이 보여줌
)
@Composable
fun AppRootScreenPreview() {
    // 만든 테마 있으면 그걸로 감싸기
    // 예: HoldRoomTheme { ... }
    MaterialTheme {
        AppRootScreen()
    }
}