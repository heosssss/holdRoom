package com.heosssss.data.mapper

import com.heosssss.data.local.entity.NotificationSettingEntity
import com.heosssss.domain.model.Notification

// domain -> data 로 변환
fun Notification.toEntity() : NotificationSettingEntity =
    NotificationSettingEntity(
        id = id,
        title = title,
        startHour = startTime.hour,
        startMinute = startTime.minute,
        endHour = endTime.hour,
        endMinute = endTime.minute,

        blockedApps = blockedApps.joinToString("|"),
        repeatType = repeatType.name,
        days = days.joinToString("|") {it.name},

        isActive = isActive,
    )