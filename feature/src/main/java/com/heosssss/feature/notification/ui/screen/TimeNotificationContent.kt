package com.heosssss.feature.notification.ui.screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.heosssss.feature.R
import com.heosssss.feature.notification.ui.component.NotiTimeCard
import com.heosssss.feature.notification.viewmodel.TimeNotificationContentViewModel

@Composable
fun TimeNotificationContent(
    modifier: Modifier = Modifier,
    viewModel: TimeNotificationContentViewModel = hiltViewModel(),
    onItemClick: (Long) -> Unit,
){
    val timeNotification by viewModel.timeNotificationlist.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.notif_time_content_title),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp, vertical = 12.dp)
        )
        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.height(16.dp))

        if (timeNotification.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "아직 지정된 시간이 없어요",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        } else {
            LazyColumn (
                verticalArrangement = Arrangement.spacedBy(1.dp)
            ){
                items(
                    items = timeNotification,
                    key = { it.id }
                ) { notification ->
                    NotiTimeCard(
                        title = notification.title,
                        timeRange = "${notification.startTime.toAmPmString()} ~ ${notification.endTime.toAmPmString()}",
                        initialChecked = notification.isActive,
                        onCheckedChange = { isActive ->
                            viewModel.onNotificationActiveChanged(notification, isActive)
                        },
                        onClick = {
                            onItemClick(notification.id)
                        }
                    )
                }
            }
        }
    }
}