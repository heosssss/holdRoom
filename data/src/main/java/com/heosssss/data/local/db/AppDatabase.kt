package com.heosssss.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.heosssss.data.local.dao.NotificationDAO
import com.heosssss.data.local.entity.NotificationTimeAlarmsEntity


@Database(
    entities = [NotificationTimeAlarmsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase(){

    abstract fun notificationSettingDao(): NotificationDAO
}