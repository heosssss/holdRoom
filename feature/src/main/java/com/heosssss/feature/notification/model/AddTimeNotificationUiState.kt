package com.heosssss.feature.notification.model

import com.heosssss.domain.model.DayOfWeek
import com.heosssss.domain.model.RepeatType
import com.heosssss.domain.model.Time

data class AddTimeNotificationUiState(
    val title: String = "",
    val startTime: Time = Time(9, 0),
    val endTime: Time = Time(18, 0),
    val blockedApps: Set<String> = emptySet(),
    val repeatType: RepeatType = RepeatType.DAILY,
    val selectedDays: Set<DayOfWeek> = DayOfWeek.entries.toSet(),

    val showStartPicker: Boolean = false,
    val showEndPicker: Boolean = false,

    val searchQuery: String = "",
    val filteredApps: List<AppInfoUiModel> = emptyList(),
    val isLoading: Boolean = false,
){
    val selectedDaysString: Set<String>
        get() = selectedDays.map { day ->
            when (day) {
                DayOfWeek.MONDAY -> "월"
                DayOfWeek.TUESDAY -> "화"
                DayOfWeek.WEDNESDAY -> "수"
                DayOfWeek.THURSDAY -> "목"
                DayOfWeek.FRIDAY -> "금"
                DayOfWeek.SATURDAY -> "토"
                DayOfWeek.SUNDAY -> "일"
            }
        }.toSet()
}