package com.heosssss.core_ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DayChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
){
    val selectedColor = Color(0xFFB39DDB)
    val bgSelected = Color(0xFFEDE7F6)
    val bgDefault = Color(0xFFF5F5F7)

    Surface(
        modifier = Modifier.size(40.dp),
        shape = CircleShape,
        color = if(selected) selectedColor else bgDefault,
        onClick = onClick
    ) {
        Box(
            contentAlignment = Alignment.Center
        ){
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                color = if(selected) Color.White else Color(0xFF424242)
            )
        }
    }
}