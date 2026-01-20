package com.heosssss.feature.notification.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.heosssss.feature.notification.model.NotificationTab


@Composable
fun NotificationTab(
    selected: NotificationTab,
    onSelectedChange: (NotificationTab) -> Unit
) {
    val items = NotificationTab.entries.toTypedArray()
    val selectedIndex = items.indexOf(selected)

    PrimaryTabRow(
        selectedTabIndex = selectedIndex,
        divider = {}
    ) {
        items.forEachIndexed { index, tab ->
            Tab(
                selected = index == selectedIndex,
                onClick = { onSelectedChange(tab) },
                text = {
                    Text(
                        text = tab.label,
                        color = if (index == selectedIndex)
                            MaterialTheme.colorScheme.outline
                        else
                            MaterialTheme.colorScheme.outlineVariant,
                        ) },

            )
        }
    }
}
