package com.heosssss.feature.record.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.heosssss.feature.record.ui.screen.RecordScreen
fun NavGraphBuilder.recordNavGraph() {
    composable<RecordMain> {
        RecordScreen()
    }
}
