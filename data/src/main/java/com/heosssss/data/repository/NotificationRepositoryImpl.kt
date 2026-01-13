package com.heosssss.data.repository

import com.heosssss.data.local.dao.NotificationDAO
import com.heosssss.data.mapper.toEntity
import com.heosssss.domain.model.Notification
import com.heosssss.domain.repository.NotificationRepository
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private  val dao: NotificationDAO
) : NotificationRepository {

    override suspend fun save(notificationSetting: Notification) {
        dao.insertNotificationSetting(notificationSetting.toEntity())
    }
}