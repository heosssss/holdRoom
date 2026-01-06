package com.heosssss.feature.focus.model

data class FocusUiState(
    val statusText: String,
    val subtitleText: String,
    val modeText: String,
    val blockedApps : List<String>
)

