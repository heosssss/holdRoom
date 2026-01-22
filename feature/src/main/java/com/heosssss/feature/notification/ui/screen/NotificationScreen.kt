package com.heosssss.feature.notification.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.heosssss.core_ui.component.AddFloatingButton
import com.heosssss.core_ui.component.AppScreenScaffold
import com.heosssss.feature.R
import com.heosssss.feature.notification.model.NotificationTab
import com.heosssss.feature.notification.navigation.AddTimeNotification
import com.heosssss.feature.notification.ui.component.NotificationTab
import com.heosssss.feature.notification.viewmodel.NotificationViewModel

@Composable
fun NotificationScreen(
    navController: NavController,
    onNavigateToAddTimeRule: () -> Unit,
    onNavigateToAddPlaceRule: () -> Unit,
    viewModel: NotificationViewModel = hiltViewModel()
){
    val selectedTab = viewModel.selectedTab

    AppScreenScaffold(
        title = stringResource(R.string.notif),
        floatingActionButton = {
            AddFloatingButton(
                onClick = {
                    when (selectedTab) {
                        NotificationTab.Time -> onNavigateToAddTimeRule()
                        NotificationTab.Place -> onNavigateToAddPlaceRule()
                    }
                }
            )
        }
        ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            NotificationTab(
                selected = selectedTab,
                onSelectedChange = { viewModel.onTabChanged(it) }
            )
            // 탭 전환은 네비게이션 보다는 상태관리로 처리함.
            // 이유 : 사용자가 탭을 옮겨가며 작업했을때 remember를 쓰면 탭이 초기화 될 수 있음
            when (selectedTab) {
                NotificationTab.Time -> TimeNotificationContent(
                    onItemClick = { id ->
                        navController.navigate(AddTimeNotification(id = id))
                    }
                )
                NotificationTab.Place -> PlaceNotificationContent()
            }
        } }
}