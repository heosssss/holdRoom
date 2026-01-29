package com.heosssss.feature.notification.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.heosssss.feature.notification.ui.screen.AddPlaceNotification
import com.heosssss.feature.notification.ui.screen.AddTimeNotification
import com.heosssss.feature.notification.ui.screen.NotificationScreen
import com.heosssss.feature.notification.ui.screen.SelectPlaceScreen


@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.notificationNavGraph(
        navController: NavController,
    ){
        composable<NotificationMain> {
            NotificationScreen(
                navController = navController,
                onNavigateToAddTimeRule = { navController.navigate(AddTimeNotification())},
                onNavigateToAddPlaceRule = { navController.navigate(AddPlaceNotification())}
            )
        }

        composable<AddTimeNotification> { backStackEntry ->
            AddTimeNotification(
                onBackClick = { navController.popBackStack() },
                onNavigateBack = { navController.popBackStack() },
            )
        }

        composable<AddPlaceNotification> {
            AddPlaceNotification(
                onBackClick = { navController.popBackStack() },
                onNavigateBack = { navController.popBackStack() },
                onClickSelectOnMap = {
                    navController.navigate(SelectPlace())
                }
            )
        }
        composable<SelectPlace> {
            SelectPlaceScreen(
                onBackClick = { navController.popBackStack() },
                onConfirmClick = { address ->
                    //todo. AddPlaceNotification으로 선택된 주소 전달!
//                    navController.previousBackStackEntry
//                        ?.savedStateHandle
//                        ?.set("address_key", address)
//                    navController.popBackStack()
                }
            )
        }

}