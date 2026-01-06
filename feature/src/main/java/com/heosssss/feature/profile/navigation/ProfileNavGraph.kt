package com.heosssss.feature.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.heosssss.feature.profile.model.ProfileMenuId
import com.heosssss.feature.profile.ui.ProfileScreen
import com.heosssss.feature.profile.ui.announcement.AnnouncementScreen
import com.heosssss.feature.profile.ui.edit.EditProfileScreen
import com.heosssss.feature.profile.ui.settings.ProfileSettingsScreen

fun NavGraphBuilder.profileNavGraph(
    navController: NavController
) {
    composable(ProfileRoutes.ROOT) {
        ProfileScreen(
            onHeaderClick = {
                navController.navigate(ProfileRoutes.EDIT_PROFILE)
            },
            onMenuClick = { menu ->
                when (menu.id) {
                    ProfileMenuId.SETTINGS -> navController.navigate(ProfileRoutes.SETTINGS)
                    ProfileMenuId.EDIT_PROFILE -> navController.navigate(ProfileRoutes.EDIT_PROFILE)
                    ProfileMenuId.ANNOUNCEMENTS -> navController.navigate(ProfileRoutes.ANNOUNCEMENTS)
//                    ProfileMenuId.FRIENDS -> navController.navigate(ProfileRoutes.FRIENDS)
                }
            },
            onLogout = {
                //todo. 로그아웃 로직 구현
            },
            onDeleteAccount = {
                //todo. 계정 삭제 로직 구현
            }
        )
    }
    composable(ProfileRoutes.SETTINGS) {
        ProfileSettingsScreen(navController)
    }

    composable(ProfileRoutes.EDIT_PROFILE) {
        EditProfileScreen(navController)
    }

    composable(ProfileRoutes.ANNOUNCEMENTS) {
        AnnouncementScreen(navController)
    }
//
//    composable(ProfileRoutes.FRIENDS) {
//        FriendsScreen(navController)
//    }
}