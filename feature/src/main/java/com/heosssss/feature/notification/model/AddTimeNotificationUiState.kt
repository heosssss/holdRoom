package com.heosssss.feature.notification.model

import com.heosssss.domain.model.DayOfWeek
import com.heosssss.domain.model.RepeatType
import com.heosssss.domain.model.Time

data class AddTimeNotificationUiState(
    val title: String = "",
    val startTime: Time? = null,
    val endTime: Time? = null,
    val blockedApps: Set<String> = emptySet(),
    val repeatType: RepeatType? = null,
    val selectedDays: Set<DayOfWeek> = emptySet(),


    val showStartPicker: Boolean = false,
    val showEndPicker: Boolean = false,

    val searchQuery: String = "",
    val filteredApps: List<AppInfoUiModel> = emptyList(),
    val isLoading: Boolean = false,
)