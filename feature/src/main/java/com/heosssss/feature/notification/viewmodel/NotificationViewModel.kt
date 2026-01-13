package com.heosssss.feature.notification.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.heosssss.feature.notification.model.NotificationTab
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
): ViewModel() {
    var selectedTab by mutableStateOf(NotificationTab.Time)
        private set

    fun onTabChanged(tab: NotificationTab) {
        selectedTab = tab
    }
}