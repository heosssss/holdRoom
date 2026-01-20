package com.heosssss.domain.model


// domian 모듈에는 규칙 + 의미만 있음.
// 알림이란 무엇인가
data class Notification(
    val id: Long = 0L,
    val title: String,
    val startTime: Time,
    val endTime: Time,
    val blockedApps: List<String>,
    val repeatType: RepeatType,
    val days: Set<DayOfWeek>,
    val isActive: Boolean,
)

data class Time(
    val hour: Int,
    val minute: Int,
){
    @Suppress("DefaultLocale")
    fun toDisplayString(): String = String.format("%02d:%02d", hour, minute)
}

enum class RepeatType {
    DAILY, WEEKLY, WEEkEND, NONE
}

enum class DayOfWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    companion object {
        val weekdayEntries = setOf(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY)
        val weekendEntries = setOf(SATURDAY, SUNDAY)
    }
}

