package com.heosssss.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.heosssss.data.local.entity.NotificationTimeAlarmsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotificationSetting(notificationSetting: NotificationTimeAlarmsEntity)

    @Query("SELECT * FROM time_alarms")
    fun getAllNotificationTimeSettings(): Flow<List<NotificationTimeAlarmsEntity>>

    @Query("SELECT * FROM time_alarms WHERE id = :id")
    suspend fun getNotificationById(id: Long): NotificationTimeAlarmsEntity

//    @Query("DELETE FROM notificationSetting WHERE id = :id")
//    suspend fun deleteNotificationSetting(id: Int)




}