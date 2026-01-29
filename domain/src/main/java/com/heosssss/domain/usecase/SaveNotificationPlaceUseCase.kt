package com.heosssss.domain.usecase

import com.heosssss.domain.model.PlaceNotification
import com.heosssss.domain.repository.NotificationPlaceRepository
import javax.inject.Inject

class SaveNotificationPlaceUseCase @Inject constructor(
    private val repository : NotificationPlaceRepository
){
    suspend operator fun invoke(notificationSetting: PlaceNotification) {
        repository.saveNotiPlace(notificationSetting)
    }
}