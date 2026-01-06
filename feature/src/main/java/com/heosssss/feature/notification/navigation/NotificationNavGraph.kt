package com.heosssss.feature.notification.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.heosssss.feature.notification.ui.screen.AddPlaceNotification
import com.heosssss.feature.notification.ui.screen.AddTimeNotification
import com.heosssss.feature.notification.ui.screen.NotificationScreen
import com.heosssss.feature.notification.ui.screen.SelectPlaceScreen


// 상세 화면 route 상수는 feature 안에서만 알고 있게 둘게
const val NOTIFICATION_ADD_TIME_ROUTE = "notification/add/time"
const val NOTIFICATION_ADD_PLACE_ROUTE = "notification/add/place"
const val NOTIFICATION_SELECT_PLACE_ROUTE = "notification/select/place"

fun NavGraphBuilder.notificationNavGraph(
        navController: NavController,
        mainRoute: String,
    ){
        composable(route = mainRoute) {
            NotificationScreen(
                onNavigateToAddTimeRule = {
                    navController.navigate(NOTIFICATION_ADD_TIME_ROUTE)
                },
                onNavigateToAddPlaceRule = {
                    navController.navigate(NOTIFICATION_ADD_PLACE_ROUTE)
                }
            )
        }

        composable(route = NOTIFICATION_ADD_TIME_ROUTE) {
            AddTimeNotification(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(route = NOTIFICATION_ADD_PLACE_ROUTE) {
            AddPlaceNotification(
                onBackClick = { navController.popBackStack() },
                onClickSelectOnMap = {
                    navController.navigate(NOTIFICATION_SELECT_PLACE_ROUTE)
                }
            )
        }
        composable(route = NOTIFICATION_SELECT_PLACE_ROUTE) {
            SelectPlaceScreen(
                onBackClick = { navController.popBackStack() },
                onConfirmClick = { address ->
                    //todo. AddPlaceNotification으로 선택된 주소 전달!
                }
            )
        }

}