package com.heosssss.data.repository

import com.heosssss.data.local.dao.NotificationPlaceDAO
import com.heosssss.data.mapper.toDomain
import com.heosssss.data.mapper.toEntity
import com.heosssss.domain.model.PlaceNotification
import com.heosssss.domain.repository.NotificationPlaceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NotificationPlaceRepositoryImpl @Inject constructor(
    private val dao: NotificationPlaceDAO
) : NotificationPlaceRepository {
    override suspend fun saveNotiPlace(placeNotification: PlaceNotification) {
        dao.insertNotificationPlaceSetting(placeNotification.toEntity())
    }

    override fun loadAllNotiPlace(): Flow<List<PlaceNotification>> {
        return dao.getAllNotificationPlaceSettings()
            .map { entities ->
                entities.map { entity ->
                    entity.toDomain()
                }
            }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun getNotiPlaceById(id: Long): PlaceNotification {
        return dao.getNotificationPlaceById(id).toDomain()
    }
}