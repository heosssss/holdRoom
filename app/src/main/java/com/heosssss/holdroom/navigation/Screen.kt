package com.heosssss.holdroom.navigation

import com.heosssss.feature.focus.navigation.FocusMain
import com.heosssss.feature.notification.navigation.NotificationMain
import com.heosssss.feature.profile.navigation.ProfileMain
import com.heosssss.feature.record.navigation.RecourdMain

//부모 타입을 상속할 수 있는 자식들을 제한함. 밑에 4개만으로 제한 함 ㅅㄱ
// sealed class로 하는 이유는? 나중에 Screen가져다 쓸때 컴파일러가 뭐 빼먹은건 있는지 아님 이상한놈이 들어가 있는지 확인해줌
sealed class Screen(val route: Any) {  //Screen 상속받는 애들은 무조건 String 하나 가져가야함
    data object Focus : Screen(FocusMain)  //object는 싱글톤 객체. 앱 전체에 딱 하나만 존재하는 인스턴스
    data object Notification : Screen(NotificationMain)
    data object Record : Screen(RecourdMain)
    data object Profile : Screen(ProfileMain)

    companion object {
        val bottomItems = listOf(Focus, Notification, Record, Profile)
    }
}


