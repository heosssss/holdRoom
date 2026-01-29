package com.heosssss.feature.notification.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heosssss.core_ui.theme.HoldRoomTheme


@Composable
fun PlaceAlarmCard(
    title : String,
    modifier: Modifier = Modifier,
    initialChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .width(160.dp)
            .height(220.dp)
            .padding(8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1EEF5))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 여기에 이미지
            Icon(
                imageVector = Icons.Default.Place,
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                tint = Color.LightGray
            )

            Spacer(modifier = Modifier.height(20.dp))
            Switch(
                checked = initialChecked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.surface,
                    checkedTrackColor = MaterialTheme.colorScheme.outline,
                    uncheckedThumbColor = MaterialTheme.colorScheme.surface,
                    uncheckedTrackColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    uncheckedBorderColor = Color.Transparent
                )
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PlaceAlarmCardPreview() {
    HoldRoomTheme {
        MaterialTheme {
            Row(modifier = Modifier.padding(16.dp)) {
                // 1. 활성화 상태 샘플 (독서실)
                PlaceAlarmCard(
                    title = "독서실",
                    initialChecked = true,
                    onCheckedChange = {},
                    onClick = {}
                )

                // 2. 비활성화 상태 샘플 (학원)
                PlaceAlarmCard(
                    title = "학원",
                    initialChecked = false,
                    onCheckedChange = {},
                    onClick = {}
                )
            }
        }
    }
}