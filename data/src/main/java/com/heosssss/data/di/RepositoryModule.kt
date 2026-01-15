package com.heosssss.data.di

import com.heosssss.data.repository.AppRepositoryImpl
import com.heosssss.data.repository.NotificationRepositoryImpl
import com.heosssss.domain.repository.AppRepository
import com.heosssss.domain.repository.NotificationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindNotificationRepository(
        notificationRepositoryImpl: NotificationRepositoryImpl
    ): NotificationRepository

    @Binds
    @Singleton //이 객체가 앱이 켜져있는동안 단 하나만 존재해야하면 singleton 붙이기
    abstract fun bindAppRepository(
        appRepositoryImpl: AppRepositoryImpl
    ): AppRepository


}