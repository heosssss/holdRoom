package com.heosssss.domain.repository

import com.heosssss.domain.model.Notification

// 얘는 그냥 껍데기임
// domain 모듈은 아무것도 의존하지 않아!
interface NotificationRepository {
    suspend fun save(notificationSetting: Notification)
//    suspend fun loadAll(): List<Notification>
}