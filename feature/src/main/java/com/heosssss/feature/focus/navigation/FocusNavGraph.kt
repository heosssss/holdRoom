package com.heosssss.feature.focus.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.heosssss.feature.focus.ui.screen.FocusScreen

fun NavGraphBuilder.focusNavGraph(
) {
    composable<FocusMain> {
        FocusScreen()
    }
}