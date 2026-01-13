package com.heosssss.feature.notification.navigation

import kotlinx.serialization.Serializable

@Serializable
object NotificationMain

@Serializable
object AddTimeNotification

@Serializable
object AddPlaceNotification

@Serializable
data class SelectPlace(
    val selectedAddress: String? = null
)