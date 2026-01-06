package com.heosssss.feature.notification.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.heosssss.core_ui.component.AddFloatingButton
import com.heosssss.core_ui.component.AppScreenScaffold
import com.heosssss.feature.notification.model.NotificationTab

@Composable
fun NotificationScreen(
    onNavigateToAddTimeRule: () -> Unit,
    onNavigateToAddPlaceRule: () -> Unit
){
    var selectedTab by remember { mutableStateOf(NotificationTab.Time) }

    AppScreenScaffold(
        title = "알림",
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
            com.heosssss.feature.notification.ui.component.NotificationTab(
                selected = selectedTab,
                onSelectedChange = { selectedTab = it }
            )
            when (selectedTab) {
                NotificationTab.Time -> TimeNotificationContent()
                NotificationTab.Place -> PlaceNotificationContent()
            }
        } }
}