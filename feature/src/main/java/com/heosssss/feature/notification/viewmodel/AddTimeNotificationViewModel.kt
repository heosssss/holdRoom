package com.heosssss.feature.notification.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heosssss.domain.model.DayOfWeek
import com.heosssss.domain.model.Notification
import com.heosssss.domain.model.RepeatType
import com.heosssss.domain.model.Time
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
    private val saveNotificationUseCase: SaveNotificationUseCase
) : ViewModel() {
    // 모든 상태를 담는 단 하나의 StateFlowt 선언
    private val _uiState = MutableStateFlow(AddTimeNotificationUiState())
    val uiState: StateFlow<AddTimeNotificationUiState> = _uiState.asStateFlow()

    // UI 이벤트 처리 함수
    fun onTitleChanged(newName: String) {
        _uiState.update { it.copy(title = newName) }
    }
    fun onStartTimeChanged(time: Time) {
        _uiState.update { it.copy(startTime = time) }
    }
    fun onEndTimeChanged(time: Time) {
        _uiState.update { it.copy(endTime = time) }
    }

    fun onRepeatTypeChanged(type: RepeatType) {
        _uiState.update { it.copy(repeatType = type) }
    }

    fun onDaysChanged(selected: Set<DayOfWeek>) {
        _uiState.update { it.copy(selectedDays = selected) }
    }

    fun onShowStartPicker(show: Boolean) {
        _uiState.update { it.copy(showStartPicker = show) }
    }

    fun onShowEndPicker(show: Boolean) {
        _uiState.update { it.copy(showEndPicker = show) }
    }

    // 반복 모드 변경 (동시에 요일 세트도 변경 가능)
    fun onRepeatModeChanged(mode: RepeatType) {
        _uiState.update { state ->
            val newDays = when(mode) {
                RepeatType.WEEKLY -> setOf(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY)
                RepeatType.WEEkEND -> setOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)
                RepeatType.DAILY -> DayOfWeek.entries.toSet()
                RepeatType.NONE -> state.selectedDays
            }
            state.copy(repeatType = mode, selectedDays = newDays)
        }
    }

    fun onDaySelected(dayString: String) {
        // 1. UI의 한글 텍스트를 Domain 모델(DayOfWeek)로 매핑
        val dayEnum = when (dayString) {
            "월" -> DayOfWeek.MONDAY
            "화" -> DayOfWeek.TUESDAY
            "수" -> DayOfWeek.WEDNESDAY
            "목" -> DayOfWeek.THURSDAY
            "금" -> DayOfWeek.FRIDAY
            "토" -> DayOfWeek.SATURDAY
            "일" -> DayOfWeek.SUNDAY
            else -> return
        }

        _uiState.update { state ->
            val currentDays = state.selectedDays
            val newDays = if (currentDays.contains(dayEnum)) {
                currentDays - dayEnum // 이미 있으면 제거
            } else {
                currentDays + dayEnum // 없으면 추가
            }
            state.copy(
                selectedDays = newDays,
                repeatType = RepeatType.NONE
            )
        }
    }

    fun saveNotificaion() {
        val currentState = _uiState.value

        viewModelScope.launch {
            saveNotificationUseCase(
                Notification(
                    title = currentState.title,
                    startTime = currentState.startTime,
                    endTime = currentState.endTime,
                    blockedApps = currentState.blockedApps.toList(),
                    repeatType = currentState.repeatType,
                    days = currentState.selectedDays,
                    isActive = true
                )
            )
        }
    }
}