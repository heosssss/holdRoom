package com.heosssss.domain.repository

import com.heosssss.domain.model.PlaceNotification
import kotlinx.coroutines.flow.Flow

interface NotificationPlaceRepository {
    suspend fun saveNotiPlace(placeNotification: PlaceNotification)
    fun loadAllNotiPlace(): Flow<List<PlaceNotification>>
    suspend fun getNotiPlaceById(id: Long): PlaceNotification
}