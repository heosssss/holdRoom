package com.heosssss.feature.profile.model

data class ProfileMenuItem(
    val id: ProfileMenuId,
    val title: String,
)


enum class ProfileMenuId {
    SETTINGS,
    EDIT_PROFILE,
    ANNOUNCEMENTS,
//    FRIENDS
}

fun defaultProfileMenus() : List<ProfileMenuItem> = listOf(
    ProfileMenuItem(ProfileMenuId.SETTINGS, "기능 설정"),
    ProfileMenuItem(ProfileMenuId.EDIT_PROFILE, "프로필"),
    ProfileMenuItem(ProfileMenuId.ANNOUNCEMENTS, "신규 공지사항"),
//    ProfileMenuItem(ProfileMenuId.FRIENDS, "친구 관리"),
)