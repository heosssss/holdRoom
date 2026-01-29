package com.heosssss.data.repository

import com.heosssss.data.local.dao.NotificationDAO
import com.heosssss.data.mapper.toDomain
import com.heosssss.data.mapper.toEntity
import com.heosssss.domain.model.DayOfWeek
import com.heosssss.domain.model.RepeatType
import com.heosssss.domain.model.Time
import com.heosssss.domain.model.TimeNotification
import com.heosssss.domain.repository.DefaultNotificationConfig
import com.heosssss.domain.repository.NotificationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
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

    override suspend fun saveNotiTime(notificationSetting: TimeNotification) {
        dao.insertNotificationSetting(notificationSetting.toEntity())
    }

    override fun loadAllNotiTime(): Flow<List<TimeNotification>> {
        return dao.getAllNotificationTimeSettings()
            .map { entities ->
                entities.map { entity ->
                    entity.toDomain()
                }
            }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun getNotiTimeById(id: Long): TimeNotification {
        return dao.getNotificationById(id).toDomain()
    }
}