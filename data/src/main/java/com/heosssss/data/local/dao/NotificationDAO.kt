package com.heosssss.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.heosssss.data.local.entity.NotificationSettingEntity

@Dao
interface NotificationDAO {

    @Insert
    suspend fun insertNotificationSetting(notificationSetting: NotificationSettingEntity)

//    @Query("SELECT * FROM notificationSetting")
//    suspend fun getAllNotificationSettings(): List<NotificationSettingEntity>
//
//    @Query("DELETE FROM notificationSetting WHERE id = :id")
//    suspend fun deleteNotificationSetting(id: Int)




}