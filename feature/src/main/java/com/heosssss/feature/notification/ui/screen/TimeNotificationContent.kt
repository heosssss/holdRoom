package com.heosssss.feature.notification.ui.screen


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TimeNotificationContent(
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "이 시간동안에는 선택한 앱들이 쉬고 있을거에요",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp, vertical = 12.dp)
        )
    }
}