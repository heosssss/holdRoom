package com.heosssss.feature.record.navigation

import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.heosssss.feature.record.ui.screen.RecordScreen
import com.heosssss.feature.record.viewmodel.RecordViewModel

fun NavGraphBuilder.recordNavGraph() {
    composable<RecourdMain> {
        RecordScreen()
    }
}
