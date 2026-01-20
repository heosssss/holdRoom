package com.heosssss.domain.repository

import com.heosssss.domain.model.DayOfWeek
import com.heosssss.domain.model.Notification
import com.heosssss.domain.model.RepeatType
import com.heosssss.domain.model.Time

// 얘는 그냥 껍데기임
// domain 모듈은 아무것도 의존하지 않아!
interface NotificationRepository {
    fun getDefaulConfig(): DefaultNotificationConfig
    suspend fun save(notificationSetting: Notification)
//    suspend fun loadAll(): List<Notification>
}


data class DefaultNotificationConfig(
    val startTime: Time,
    val endTime: Time,
    val repeatType: RepeatType,
    val selectedDays: Set<DayOfWeek>
)