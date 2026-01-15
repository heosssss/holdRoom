package com.heosssss.feature.notification.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
            text = "설치된 앱이 없습니다.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

