package com.heosssss.data.mapper

import com.heosssss.data.local.entity.NotificationTimeAlarmsEntity
import com.heosssss.domain.model.DayOfWeek
import com.heosssss.domain.model.RepeatType
import com.heosssss.domain.model.Time
import com.heosssss.domain.model.TimeNotification

// domain -> data 로 변환
fun TimeNotification.toEntity() : NotificationTimeAlarmsEntity =
    NotificationTimeAlarmsEntity(
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

// data -> domain 로 변환
fun NotificationTimeAlarmsEntity.toDomain() : TimeNotification =
    TimeNotification(
        id = id,
        title = title,
        startTime = Time(hour = startHour, minute = startMinute),
        endTime = Time(hour = endHour, minute = endMinute),
        blockedApps = if (blockedApps.isEmpty()) emptyList() else blockedApps.split("|"),
        repeatType = RepeatType.valueOf(repeatType),
        days = if (days.isEmpty()) emptySet() else days.split("|").map { DayOfWeek.valueOf(it) }.toSet(),
        isActive = isActive
    )