package com.heosssss.holdroom.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddReaction
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    navController: NavController // 네비게이션을 실제로 수행하는 컨트롤러.
){
    val items = Screen.bottomItems

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Column (
            modifier = Modifier
                .shadow(
                    elevation = 6.dp,
                    shape = RoundedCornerShape(28.dp),
                )
                .clip(RoundedCornerShape(28.dp))
                .background(Color.White)
        ){
            NavigationBar(
                containerColor = Color.Transparent,
            ) {
                // 선택된 route를 가져오기
                val navBackStackEntry by navController.currentBackStackEntryAsState() // 현재 네비게이션 백스택의 top을 state로 가져옴 by로 위임함
                val currentRoute = navBackStackEntry?.destination?.route // 지금 화면에 보이는 route의 문자열
                // 각 스크린에 대해 NavigationBarItem만들기
                items.forEach { screen ->
                    NavigationBarItem(
                        selected = currentRoute == screen.route, // 이 바텀 탭이 현재 선택된 상태인지 여부 파악 (true/false)
                        onClick = {
                            navController.navigate(screen.route) { // 화면 이동
                                launchSingleTop = true  // 현재 백스택의 맨 위가 이미 같은 route면 기존 것을 재사용함
                                popUpTo(navController.graph.startDestinationId) { // 지정한 대상(시작화면 ID)까지 백스택을 위로 정리함
                                    saveState = true // 상태 저장
                                }
                                restoreState = true // 저장 상태 있으면 해당 상태를 복원
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = when (screen) {
                                    Screen.Focus -> Icons.Outlined.Timer
                                    Screen.Notification -> Icons.Outlined.Notifications
                                    Screen.Record -> Icons.Outlined.AddReaction
                                    Screen.Profile -> Icons.Outlined.Person
                                },
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                text = when (screen) {
                                    Screen.Focus -> "집중"
                                    Screen.Notification -> "알림"
                                    Screen.Record -> "기록"
                                    Screen.Profile -> "프로필"
                                }
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = Color.Transparent,

                            unselectedIconColor = MaterialTheme.colorScheme.outlineVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.outlineVariant,
                        )
                    )
                }
            }
        }
    }
}