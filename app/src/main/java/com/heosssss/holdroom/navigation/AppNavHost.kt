package com.heosssss.holdroom.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.heosssss.feature.focus.navigation.focusNavGraph
import com.heosssss.feature.notification.navigation.notificationNavGraph
import com.heosssss.feature.profile.navigation.profileNavGraph
import com.heosssss.feature.record.navigation.recordNavGraph

@Composable // 컴포즈 함수에요. 그 말은 즉? UI그리는 함수라는 거죠
fun AppNavHost(
    navController: NavHostController,   // 화면간 이동을 실제로 실행하는 컨트롤러
    modifier: Modifier = Modifier,      // 외부호출시 Modifier를 파라미터로 받아서 꾸미기를 적용할 수 있게 해줌
){
    NavHost(
        navController = navController,      // navigate(), popBackStack() 등의 동작을 수행.
        startDestination = Screen.Focus.route, // AppNavHost가 처음 만들어 질때 가장 먼저 보여줄 화면의 route
        modifier = modifier
    ){

        // 집중 네비게이션
        focusNavGraph()

        // 알림 네비게이션
        notificationNavGraph(navController)

        // 기록 네비게이션
        recordNavGraph()

        // 프로필 네비게이션
        profileNavGraph(navController)
    }
}