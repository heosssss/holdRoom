package com.heosssss.feature.notification.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.heosssss.feature.R
import com.heosssss.feature.notification.model.AppInfoUiModel


@Composable
fun AppList(
    installedApps: List<AppInfoUiModel>, // 데이터만 받음
    selectedApps: Set<String>,
    onAppToggle: (String) -> Unit
    ){
        if(installedApps.isEmpty()) {
            EmptyAppList()
        }else{
            installedApps.forEach { app ->
                AppToggleRow(
                    name = app.name,
                    icon = app.icon,
                    enabled = selectedApps.contains(app.packageName),
                    onEnabledChange = { onAppToggle(app.packageName) }
                )
            }
        }
    }

@Composable
fun EmptyAppList() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.notif_empty_app_list),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    }
}

