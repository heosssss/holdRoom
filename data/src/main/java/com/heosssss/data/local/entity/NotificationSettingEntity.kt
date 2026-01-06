package com.heosssss.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notification_settings")
data class NotificationSettingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val enabled: Boolean,
    val hour: Int,
    val minute: Int,
    val repeatType: String
)