package com.heosssss.domain.model

data class PlaceNotification(
    val id: Long = 0L,
    val title: String,
    val latitude: Double,
    val longitude: Double,
    val address: String?,
    val blockedApps: List<String>,
    val isActive: Boolean,

)