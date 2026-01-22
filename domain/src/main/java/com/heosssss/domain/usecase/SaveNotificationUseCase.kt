package com.heosssss.domain.usecase

import com.heosssss.domain.model.TimeNotification
import com.heosssss.domain.repository.NotificationRepository
import javax.inject.Inject


//usecase에는 단일 책임의 원칙(SRP)을 적용하여 1개의 액션을 담당
class SaveNotificationUseCase @Inject constructor(
    private val repository : NotificationRepository
){
    suspend operator fun invoke(notificationSetting: TimeNotification){
        repository.notiTimeSave(notificationSetting)
    }
}
