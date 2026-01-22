package com.heosssss.domain.repository

import com.heosssss.domain.model.DayOfWeek
import com.heosssss.domain.model.RepeatType
import com.heosssss.domain.model.Time
import com.heosssss.domain.model.TimeNotification
import kotlinx.coroutines.flow.Flow

// 얘는 그냥 껍데기임
// domain 모듈은 아무것도 의존하지 않아!
interface NotificationRepository {
    fun getDefaulConfig(): DefaultNotificationConfig
    suspend fun notiTimeSave(notificationSetting: TimeNotification)
    fun loadAllNotiTime(): Flow<List<TimeNotification>>
    suspend fun getNotiTimeById(id: Long): TimeNotification
}


data class DefaultNotificationConfig(
    val startTime: Time,
    val endTime: Time,
    val repeatType: RepeatType,
    val selectedDays: Set<DayOfWeek>
)