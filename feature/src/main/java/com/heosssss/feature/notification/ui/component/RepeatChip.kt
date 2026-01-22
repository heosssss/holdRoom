package com.heosssss.feature.notification.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heosssss.core_ui.theme.HoldRoomTheme

@Composable
fun RepeatChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
){
    val selectedColor = MaterialTheme.colorScheme.outline
    val bgDefault = MaterialTheme.colorScheme.surface
    val borderColor = MaterialTheme.colorScheme.onSurfaceVariant
    val textColor = MaterialTheme.colorScheme.onSurface

    Surface(
        shape = RoundedCornerShape(10.dp),
        color = if (selected) selectedColor else bgDefault,
        border = if(selected) null else BorderStroke(1.dp, borderColor),
        onClick = onClick,
        modifier = Modifier
            .height(48.dp)
            .defaultMinSize(minWidth = 120.dp)
    ){
        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                color = if(selected) bgDefault else textColor,
            )
        }

    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun RepeatChipPreview() {
    HoldRoomTheme {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text("선택 안 된 칩")
            RepeatChip(
                text = "주중",
                selected = false,
                onClick = {}
            )

            Spacer(Modifier.height(16.dp))

            Text("선택된 칩")
            RepeatChip(
                text = "매일",
                selected = true,
                onClick = {}
            )
        }
    }
}

