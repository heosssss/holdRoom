package com.heosssss.feature.notification.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heosssss.domain.model.DayOfWeek
import com.heosssss.domain.model.RepeatType
import com.heosssss.domain.model.Time
import com.heosssss.domain.model.TimeNotification
import com.heosssss.domain.usecase.GetDefaultConfigUseCase
import com.heosssss.domain.usecase.GetNotificationTimeUseCase
import com.heosssss.domain.usecase.SaveNotificationUseCase
import com.heosssss.feature.notification.model.AddTimeNotificationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTimeNotificationViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val saveNotificationUseCase: SaveNotificationUseCase,
    private val getDefaultConfigUseCase: GetDefaultConfigUseCase,
    private val getNotificationUseCase: GetNotificationTimeUseCase,
) : ViewModel() {

    private val notificationId: Long? = savedStateHandle["id"]

    private val _uiState = MutableStateFlow(AddTimeNotificationUiState())
    val uiState: StateFlow<AddTimeNotificationUiState> = _uiState.asStateFlow()

    val _saveSuccessEvent = MutableSharedFlow<Unit>()
    val saveSuccessEvent = _saveSuccessEvent.asSharedFlow()

    init {
        if (notificationId != null && notificationId != -1L) {
            loadExistingNotification(notificationId)
        } else {
            loadDefaultConfig()
        }
    }

    private fun loadDefaultConfig() {
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

    private fun loadExistingNotification(id: Long) {
        viewModelScope.launch {
            try {
                val notification = getNotificationUseCase(id)
                Log.d("CheckData", "불러온 앱 개수: ${notification.blockedApps.size}") // 로그 추가
                _uiState.update {
                    it.copy(
                        title = notification.title,
                        startTime = notification.startTime,
                        endTime = notification.endTime,
                        repeatType = notification.repeatType,
                        selectedDays = notification.days,
                        blockedApps = notification.blockedApps.toSet(),
                        // 추가로 알림 활성화 여부 등 필요하다면 추가
                    )
                }
                // TODO: AppListViewModel에 notification.blockedApps 정보를 전달하여 체크박스를 채워야 합니다.
            } catch (e: Exception) {
                Log.e("TimeViewModel", "데이터 로드 실패: ${e.message}")
            }
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
            val startTime = System.currentTimeMillis()

            saveNotificationUseCase(
                TimeNotification(
                    id = notificationId ?: 0L,
                    title = currentState.title,
                    startTime = currentState.startTime ?: Time(9, 0),
                    endTime = currentState.endTime ?: Time(18, 0),
                    blockedApps = selectedApps,
                    repeatType = currentState.repeatType ?: RepeatType.DAILY,
                    days = currentState.selectedDays,
                    isActive = true
                )
            )

            // todo. 확인 후 지우기
            val endTime = System.currentTimeMillis()
            Log.d("Performance", "저장 완료까지 걸린 시간: ${endTime - startTime}ms")

            _saveSuccessEvent.emit(Unit)
        }
    }
}