package com.heosssss.feature.focus.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.heosssss.core_ui.component.BottomActionButton
import com.heosssss.feature.focus.model.FocusUiState
import com.heosssss.feature.focus.ui.component.FocusCharacter
import com.heosssss.feature.focus.ui.component.FocusHeader
import com.heosssss.feature.focus.ui.component.FocusStateCard

@Composable
fun FocusContent(
    uiState: FocusUiState,
    onPauseClick: () -> Unit
){
    val backgroundColor = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF9D4EDD),
            Color(0xFF7A2CD9)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(
                    WindowInsets.safeDrawing // 상태바/하단 제스처 피해줌
                )
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FocusHeader(uiState)
            Spacer(modifier = Modifier.height(32.dp))
            FocusCharacter()
            Spacer(modifier = Modifier.height(32.dp))
            FocusStateCard(uiState)
            Spacer(modifier = Modifier.weight(1f))
            BottomActionButton(
                text = "잠시 해제하기",
                onClick = onPauseClick,
                containerColor = Color(0xFFB38CFF),
                contentColor = Color.White
            )

        }
    }
}