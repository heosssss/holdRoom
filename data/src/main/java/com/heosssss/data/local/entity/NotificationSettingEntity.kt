package com.heosssss.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


//DB 구조 생성
@Entity(tableName = "time_alarms")
data class NotificationTimeAlarmsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    val title: String,
    val startHour: Int,
    val startMinute: Int,
    val endHour: Int,
    val endMinute: Int,

    val blockedApps: String,
    val repeatType: String,
    val days: String,

    val isActive: Boolean,
)