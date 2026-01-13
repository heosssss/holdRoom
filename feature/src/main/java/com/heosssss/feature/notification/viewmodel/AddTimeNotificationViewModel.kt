package com.heosssss.feature.notification.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heosssss.domain.model.DayOfWeek
import com.heosssss.domain.model.Notification
import com.heosssss.domain.model.RepeatType
import com.heosssss.domain.model.Time
import com.heosssss.domain.usecase.SaveNotificationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTimeNotificationViewModel @Inject constructor(
    private val saveNotificationUseCase: SaveNotificationUseCase
) : ViewModel() {
    // UI 상태
    var timeName by mutableStateOf("")
        private set
    var startTime by mutableStateOf(Time(9, 0))
        private set

    var endTime by mutableStateOf(Time(0, 0))
        private set

    var blockedApps by mutableStateOf<List<String>>(emptyList())
        private set

    var repeatType by mutableStateOf(RepeatType.DAILY)
        private set

    var days by mutableStateOf<Set<DayOfWeek>>(emptySet())
        private set



    // UI 이벤트 처리 함수
    fun onTimeNameChanged(newName: String) {
        timeName = newName
    }
    fun onStartTimeChanged(time: Time) {
        startTime = time
    }

    fun onEndTimeChanged(time: Time) {
        endTime = time
    }

    fun onBlockedAppsChanged(apps: List<String>) {
        blockedApps = apps
    }

    fun onRepeatTypeChanged(type: RepeatType) {
        repeatType = type
    }

    fun onDaysChanged(selected: Set<DayOfWeek>) {
        days = selected
    }

    fun saveNotificaion() {
        viewModelScope.launch {
            saveNotificationUseCase(
                Notification(
                    timeName = timeName,
                    startTime = startTime,
                    endTime = endTime,
                    blockedApps = blockedApps,
                    repeatType = repeatType,
                    days = days,
                    enabled = true
                )
            )
        }
    }
}