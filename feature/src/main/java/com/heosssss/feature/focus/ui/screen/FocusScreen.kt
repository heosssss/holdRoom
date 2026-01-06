package com.heosssss.feature.focus.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.heosssss.feature.focus.ui.component.PauseBottomSheet
import com.heosssss.feature.focus.viewmodel.FocusViewModel

@Composable
fun FocusScreen(
    viewModel: FocusViewModel = viewModel(),
    ) {
    //collectAsState 보다 collectAsStateWithLifecycle 이것 사용하길 권장함.
    //collectAsStateWithLifecycle >> 이게 가장 최신 문법임. 안드로이드 라이프사이클에 맞춰 리소스를 절약하게 해줌요
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val onPauseClick = viewModel::onPauseClick
    val showSheet = viewModel.showPauseSheet

    if(showSheet){
        PauseBottomSheet(
            onDismiss = viewModel::onDismissSheet,
            onConfirm = viewModel::onConfirmPause
        )
    }

    FocusContent(
        uiState = uiState,
        onPauseClick = onPauseClick
    )
}

