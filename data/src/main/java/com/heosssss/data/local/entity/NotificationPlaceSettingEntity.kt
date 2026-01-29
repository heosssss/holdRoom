package com.heosssss.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "place_alarms")
data class NotificationPlaceAlarmsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    val title: String,
    val latitude: Double,
    val longitude: Double,
    val address: String?,

    val blockedApps: String,
    val isActive: Boolean,
)