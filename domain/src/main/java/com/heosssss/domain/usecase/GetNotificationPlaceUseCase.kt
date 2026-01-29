package com.heosssss.domain.usecase

import com.heosssss.domain.repository.NotificationPlaceRepository
import javax.inject.Inject

class GetNotificationPlaceUseCase @Inject constructor(
    private val repository: NotificationPlaceRepository
){
    suspend operator fun invoke(id: Long) = repository.getNotiPlaceById(id)

}