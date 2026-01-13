package com.heosssss.feature.focus.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.heosssss.feature.focus.model.FocusUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FocusViewModel : ViewModel(){
    private val _uiState = MutableStateFlow( // MutableStateFlow : 데이터 수정이 가능함. 내부 수정용
        FocusUiState(
            statusText = "지금 집중 중이에요",
            subtitleText = "해파리가 이미 집중 모드로 들어갔어요!",
            modeText = "시간 기반 차단 활성화",
            blockedApps = listOf("Instagram", "YouTube", "TikTok")
        )
    )
    val uiState: StateFlow<FocusUiState> = _uiState // StateFlow : 데이터 수정 불가능 외부 노출용 / 핫 스트림
    var showPauseSheet by mutableStateOf(false)
        private set  // 해당 변수를 값을 수정하는 기능만 private으로 만들겠다
    // 읽기 쓰기 모두 비공개로 하고 싶다면 "private var" 라고 하면 됨

    fun onPauseClick(){
        showPauseSheet = true
    }

    fun onDismissSheet() {
        showPauseSheet = false
    }

    fun onConfirmPause(minutes: Int) {
        // TODO 실제 집중 해제 로직
        showPauseSheet = false
    }

}