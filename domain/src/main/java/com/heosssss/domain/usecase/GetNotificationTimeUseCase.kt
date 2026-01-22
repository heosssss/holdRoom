package com.heosssss.domain.usecase

import com.heosssss.domain.repository.NotificationRepository
import javax.inject.Inject

class GetNotificationTimeUseCase @Inject constructor(
    private val repository: NotificationRepository
){
    suspend operator fun invoke(id: Long) = repository.getNotiTimeById(id)
}