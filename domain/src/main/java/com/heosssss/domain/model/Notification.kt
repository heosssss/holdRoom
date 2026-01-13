package com.heosssss.holdroom.domain


// domian 모듈에는 규칙 + 의미만 있음.
// 알림이란 무엇인가
data class Notification(
    val id: Long = 0L,
    val hour: Int,
    val minute: Int,
//    val repeatType: RepeatType,
    val enabled: Boolean,
)