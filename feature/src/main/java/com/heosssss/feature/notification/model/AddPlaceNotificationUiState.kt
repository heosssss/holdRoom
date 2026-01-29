package com.heosssss.feature.notification.model

data class AddPlaceNotificationUiState(
    val title: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val address: String? = null,
    val blockedApps: Set<String> = emptySet(),

    val searchQuery: String = "",
    val filteredApps: List<AppInfoUiModel> = emptyList(),
    val isLoading: Boolean = false,

)