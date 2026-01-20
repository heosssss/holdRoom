package com.heosssss.feature.notification.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heosssss.domain.model.DayOfWeek
import com.heosssss.domain.model.Notification
import com.heosssss.domain.model.RepeatType
import com.heosssss.domain.model.Time
import com.heosssss.domain.usecase.GetDefaultConfigUseCase
import com.heosssss.domain.usecase.SaveNotificationUseCase
import com.heosssss.feature.notification.model.AddTimeNotificationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTimeNotificationViewModel @Inject constructor(
    private val saveNotificationUseCase: SaveNotificationUseCase,
    private val getDefaultConfigUseCase: GetDefaultConfigUseCase
) : ViewModel() {
    // 모든 상태를 담는 단 하나의 StateFlow선언
    private val _uiState = MutableStateFlow(AddTimeNotificationUiState())
    val uiState: StateFlow<AddTimeNotificationUiState> = _uiState.asStateFlow()

    init {
        val defaultConfig = getDefaultConfigUseCase()
        _uiState.update {
            it.copy(
                startTime = defaultConfig.startTime,
                endTime = defaultConfig.endTime,
                repeatType = defaultConfig.repeatType,
                selectedDays = defaultConfig.selectedDays
            )
        }
    }
    
    fun onShowStartPicker(show: Boolean) {
        _uiState.update { it.copy(showStartPicker = show) }
    }

    fun onShowEndPicker(show: Boolean) {
        _uiState.update { it.copy(showEndPicker = show) }
    }
    fun onTitleChanged(newName: String) {
        _uiState.update { it.copy(title = newName) }
    }
    fun onStartTimeChanged(time: Time) {
        _uiState.update { it.copy(startTime = time) }
    }
    fun onEndTimeChanged(time: Time) {
        _uiState.update { it.copy(endTime = time) }
    }
    
    fun onRepeatModeChanged(mode: RepeatType, days: Set<DayOfWeek>) {
        _uiState.update {
            it.copy(
                repeatType = mode,
                selectedDays = days 
            )
        }
    }

    fun onDaySelected(day: DayOfWeek) {
        _uiState.update { state ->
            val currentSelected = state.selectedDays

            val newSelected = if (currentSelected.contains(day)) {
                currentSelected - day
            } else {
                currentSelected + day
            }

            val newRepeatType = when {
                // 모든 요일이 선택된 경우 (월~일 7개)
                newSelected.size == 7 -> RepeatType.DAILY
                newSelected == DayOfWeek.weekdayEntries -> RepeatType.WEEKLY
                newSelected == DayOfWeek.weekendEntries -> RepeatType.WEEkEND
                else -> RepeatType.NONE
            }

            state.copy(
                selectedDays = newSelected,
                repeatType = newRepeatType)
        }
    }
    fun saveNotificaion(selectedApps: List<String>) {
        val currentState = _uiState.value

        viewModelScope.launch {
            saveNotificationUseCase(
                Notification(
                    title = currentState.title,
                    startTime = currentState.startTime ?: Time(9, 0),
                    endTime = currentState.endTime ?: Time(18, 0),
                    blockedApps = selectedApps,
                    repeatType = currentState.repeatType ?: RepeatType.DAILY,
                    days = currentState.selectedDays,
                    isActive = true
                )
            )
        }
    }
}