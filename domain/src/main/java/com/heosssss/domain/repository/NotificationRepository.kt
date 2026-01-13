package com.heosssss.holdroom.repository

interface NotificationRepository {
    suspend fun save(notificationSetting: NotificationSettingEntity)
//    suspend fun loadAll(): List<NotificationSettingEntity>
}