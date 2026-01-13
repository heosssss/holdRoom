package com.heosssss.data.repository

import com.heosssss.data.local.dao.NotificationDAO
import com.heosssss.data.local.entity.NotificationSettingEntity

class NotificationRepository(
    private  val dao: NotificationDAO
) {
    suspend fun save(notificationSetting: NotificationSettingEntity) {
        dao.insertNotificationSetting(notificationSetting)
    }

    suspend fun loadAll(): List<NotificationSettingEntity> {
        return dao.getAllNotificationSettings()
    }
}