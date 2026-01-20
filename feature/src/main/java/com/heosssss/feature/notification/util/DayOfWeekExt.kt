package com.heosssss.feature.notification.util

import androidx.annotation.StringRes
import com.heosssss.core_ui.R
import com.heosssss.domain.model.DayOfWeek

@StringRes
fun DayOfWeek.toResId(): Int = when (this) {
    DayOfWeek.MONDAY -> R.string.day_mon
    DayOfWeek.TUESDAY -> R.string.day_tue
    DayOfWeek.WEDNESDAY -> R.string.day_wed
    DayOfWeek.THURSDAY -> R.string.day_thu
    DayOfWeek.FRIDAY -> R.string.day_fri
    DayOfWeek.SATURDAY -> R.string.day_sat
    DayOfWeek.SUNDAY -> R.string.day_sun
}