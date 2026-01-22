package com.heosssss.feature.notification.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heosssss.domain.model.TimeNotification
import com.heosssss.domain.repository.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimeNotificationContentViewModel  @Inject constructor(
    private val repostitory: NotificationRepository
) : ViewModel(){
    val timeNotificationlist: StateFlow<List<TimeNotification>> = repostitory.loadAllNotiTime()
        .flowOn(Dispatchers.IO)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun onNotificationActiveChanged(notification: TimeNotification, isActive: Boolean){
        viewModelScope.launch {
            val updatedNotification = notification.copy(isActive = isActive)
            repostitory.notiTimeSave(updatedNotification)
        }
    }
}