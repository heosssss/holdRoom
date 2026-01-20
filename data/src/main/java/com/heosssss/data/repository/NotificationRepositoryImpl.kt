package com.heosssss.data.repository

import com.heosssss.data.local.dao.NotificationDAO
import com.heosssss.data.mapper.toEntity
import com.heosssss.domain.model.DayOfWeek
import com.heosssss.domain.model.Notification
import com.heosssss.domain.model.RepeatType
import com.heosssss.domain.model.Time
import com.heosssss.domain.repository.DefaultNotificationConfig
import com.heosssss.domain.repository.NotificationRepository
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private  val dao: NotificationDAO
) : NotificationRepository {
    override fun getDefaulConfig(): DefaultNotificationConfig {
        return DefaultNotificationConfig(
            startTime = Time(9, 0),
            endTime = Time(18, 0),
            repeatType = RepeatType.DAILY,
            selectedDays = DayOfWeek.entries.toSet()
        )
    }

    override suspend fun save(notificationSetting: Notification) {
        dao.insertNotificationSetting(notificationSetting.toEntity())
    }
}