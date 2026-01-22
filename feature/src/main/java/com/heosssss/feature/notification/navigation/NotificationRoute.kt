package com.heosssss.feature.notification.navigation

import kotlinx.serialization.Serializable

@Serializable
object NotificationMain

@Serializable
data class AddTimeNotification(
    val id: Long? = null
)

@Serializable
object AddPlaceNotification
@Serializable
data class SelectPlace(
    val selectedAddress: String? = null
)