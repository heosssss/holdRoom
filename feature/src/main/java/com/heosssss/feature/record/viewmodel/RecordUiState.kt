package com.heosssss.feature.record.viewmodel

import com.heosssss.feature.record.model.BlockedAppItem
import com.heosssss.feature.record.model.PuzzleProgress
import com.heosssss.feature.record.model.WeeklyRecordSummary

data class RecordUiState(
    val weekly: WeeklyRecordSummary = WeeklyRecordSummary(
        totalFocusMinutes = 0,
        focusSessions = 0
    ),
    val puzzle: PuzzleProgress = PuzzleProgress(
        unlockedPieces = 0,
        totalPieces = 25,
        hasUnlockTicket = false
    ),
    val blockedTop3: List<BlockedAppItem> = emptyList(),
    val reflectionMessage: String = "기록이 쌓이고 있어요",
    val isLoading: Boolean = false
)