package com.heosssss.feature.record.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.heosssss.core_ui.component.AppScreenScaffold
import com.heosssss.feature.record.ui.component.BlockedAppsSection
import com.heosssss.feature.record.ui.component.FocusPuzzleSection
import com.heosssss.feature.record.ui.component.ReflectionMessage
import com.heosssss.feature.record.ui.component.WeeklySummaryCard
import com.heosssss.feature.record.viewmodel.RecordViewModel

@Composable
fun RecordScreen(
    viewModel: RecordViewModel = viewModel(),
    modifier: Modifier = Modifier,
){
    val state = viewModel.uiState.collectAsState().value

    AppScreenScaffold(title = "기록"){ innerPadding ->
        Column(Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState())
        ){
            Spacer(Modifier.height(16.dp))

            WeeklySummaryCard(
                totalFocusMinutes = state.weekly.totalFocusMinutes,
                focusSessions = state.weekly.focusSessions
            )

            Spacer(Modifier.height(16.dp))

            FocusPuzzleSection(
                progress = state.puzzle,
                onClickUnlock = viewModel::onClickUnlockPuzzlePiece
            )

            Spacer(Modifier.height(16.dp))

            BlockedAppsSection(
                top3 = state.blockedTop3
            )

            Spacer(Modifier.height(16.dp))

            ReflectionMessage(message = state.reflectionMessage)

            Spacer(Modifier.height(16.dp))

        }
    }
}