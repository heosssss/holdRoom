package com.heosssss.feature.notification.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.heosssss.feature.notification.ui.component.PlaceAlarmCard
import com.heosssss.feature.notification.viewmodel.PlaceNotificationContentViewModel

@Composable
fun PlaceNotificationContent(
    modifier: Modifier = Modifier,
    viewModel: PlaceNotificationContentViewModel = hiltViewModel(),
    onItemClick: (Long) -> Unit,
){
    val placeNotification by viewModel.placeNotificationlist.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.notif_place_content_title),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp, vertical = 12.dp)
        )
        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.height(16.dp))

        if(placeNotification.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "아직 지정된 장소가 없어요",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        } else {
            LazyVerticalGrid(
                // 한 줄에 2개씩 고정
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    items = placeNotification,
                    key = { it.id }
                ) { item ->
                    PlaceAlarmCard(
                        title = item.title,
                        initialChecked = item.isActive,
                        modifier = Modifier.fillMaxWidth(),
                        onCheckedChange = { isActive ->
                            viewModel.onNotificationActiveChanged(item, isActive)
                        },
                        onClick = { onItemClick(item.id) }
                    )
                }
            }
        }
    }
}