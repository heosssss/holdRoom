package com.heosssss.core_ui.component

import androidx.compose.foundation.BorderStroke
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

@Composable
fun RepeatChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
){
    val selectedColor = Color(0xFFB39DDB)
    val bgSelected = Color(0xFFEDE7F6)
    val bgDefault = Color(0xFFF5F5F7)

    Surface(
        shape = RoundedCornerShape(10.dp),
        color = if (selected) selectedColor else bgDefault,
        border = if(selected) null else BorderStroke(1.dp, Color(0xFFE0E0E0)),
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
                color = if(selected) Color.White else Color(0xFF424242),
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun RepeatChipPreview() {
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

