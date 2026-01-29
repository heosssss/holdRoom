package com.heosssss.feature.notification.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heosssss.domain.model.PlaceNotification
import com.heosssss.domain.usecase.GetNotificationPlaceUseCase
import com.heosssss.domain.usecase.SaveNotificationPlaceUseCase
import com.heosssss.feature.notification.model.AddPlaceNotificationUiState
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
class AddPlaceNotificationViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val saveNotificationPlaceUseCase: SaveNotificationPlaceUseCase,
    private val getNotificationPlaceUseCase: GetNotificationPlaceUseCase,
): ViewModel(){
    private val notificationId: Long? = savedStateHandle["id"]

    private val _uiState = MutableStateFlow(AddPlaceNotificationUiState())
    val uiState : StateFlow<AddPlaceNotificationUiState> = _uiState.asStateFlow()

    val _saveSuccessEvent = MutableSharedFlow<Unit>()
    val saveSuccessEvent = _saveSuccessEvent.asSharedFlow()

    init {
        if (notificationId != null && notificationId != -1L) {
            loadExistingNotification(notificationId)
        }
    }


    private fun loadExistingNotification(id: Long) {
        viewModelScope.launch {
            try {
                val notification = getNotificationPlaceUseCase(id)
                _uiState.update {
                    it.copy(
                        title = notification.title,
                        latitude = notification.latitude,
                        longitude = notification.longitude,
                        address = notification.address,
                        blockedApps = notification.blockedApps.toSet(),
                    )
                }
            } catch (e: Exception) {
                Log.e("PlaceViewModel", "데이터 로드 실패: ${e.message}")
            }
        }
    }

    fun onTitleChanged(newName: String) {
        _uiState.update { it.copy(title = newName) }
    }

    fun saveNotificaion(selectedApps: List<String>){
        val currentState = _uiState.value

        viewModelScope.launch {
            saveNotificationPlaceUseCase(
                PlaceNotification(
                    id = notificationId ?: 0L,
                    title = currentState.title,
                    latitude = currentState.latitude,
                    longitude = currentState.longitude,
                    address = currentState.address,
                    blockedApps = selectedApps,
                    isActive = true
                )
            )
            _saveSuccessEvent.emit(Unit)
        }
    }

}