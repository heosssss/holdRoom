package com.heosssss.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.heosssss.data.local.entity.NotificationPlaceAlarmsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationPlaceDAO{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotificationPlaceSetting(notificationpPlaceSetting: NotificationPlaceAlarmsEntity)

    @Query("SELECT * FROM place_alarms")
    fun getAllNotificationPlaceSettings(): Flow<List<NotificationPlaceAlarmsEntity>>

    @Query("SELECT * FROM place_alarms WHERE id = :id")
    suspend fun getNotificationPlaceById(id: Long): NotificationPlaceAlarmsEntity
}