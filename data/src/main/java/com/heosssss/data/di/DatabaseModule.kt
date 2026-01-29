package com.heosssss.data.di

import android.content.Context
import androidx.room.Room
import com.heosssss.data.local.dao.NotificationDAO
import com.heosssss.data.local.dao.NotificationPlaceDAO
import com.heosssss.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "holdRoom_db"
        ).build()

    @Provides
    fun provideNotificationSettingDao(
        db: AppDatabase
    ) : NotificationDAO =
        db.notificationSettingDao()

    @Provides
    fun provideNotificationPlaceSettingDao(
        db: AppDatabase
    ) : NotificationPlaceDAO =
        db.notificationPlaceSettingDao()



}