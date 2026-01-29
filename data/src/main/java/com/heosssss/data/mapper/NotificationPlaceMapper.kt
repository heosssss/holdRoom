package com.heosssss.data.mapper

import com.heosssss.data.local.entity.NotificationPlaceAlarmsEntity
import com.heosssss.domain.model.PlaceNotification

fun PlaceNotification.toEntity(): NotificationPlaceAlarmsEntity =
    NotificationPlaceAlarmsEntity(
        id = id,
        title = title,
        latitude = latitude,
        longitude = longitude,
        address = address,

        blockedApps = blockedApps.joinToString("|"),
        isActive = isActive,
    )

fun NotificationPlaceAlarmsEntity.toDomain(): PlaceNotification =
    PlaceNotification(
        id = id,
        title = title,
        latitude = latitude,
        longitude = longitude,
        address = address,
        blockedApps = if (blockedApps.isEmpty()) emptyList() else blockedApps.split("|"),
        isActive = isActive
    )