package com.heosssss.domain.usecase

import com.heosssss.domain.repository.NotificationRepository
import javax.inject.Inject

class GetDefaultConfigUseCase @Inject constructor(
    private val repository: NotificationRepository
){
    operator fun invoke() = repository.getDefaulConfig()
}