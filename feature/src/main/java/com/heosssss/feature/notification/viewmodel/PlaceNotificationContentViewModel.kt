package com.heosssss.feature.notification.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heosssss.domain.model.PlaceNotification
import com.heosssss.domain.repository.NotificationPlaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaceNotificationContentViewModel @Inject constructor(
    private val repository: NotificationPlaceRepository
) : ViewModel(){
    val placeNotificationlist: StateFlow<List<PlaceNotification>> = repository.loadAllNotiPlace()
        .flowOn(Dispatchers.IO)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun onNotificationActiveChanged(notification: PlaceNotification, isActive: Boolean){
        viewModelScope.launch {
            val updatedNotification = notification.copy(isActive = isActive)
            repository.saveNotiPlace(updatedNotification)
        }
    }
}