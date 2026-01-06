package com.heosssss.core_ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AddFloatingButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lilac = Color(0xFFF5EFFD)
    val lilacBg = Color(0xFFB39DDB)

    Surface(
        modifier = modifier.size(56.dp),   // FAB 기본 크기
        shape = RoundedCornerShape(50),     // 완전 원
        color = lilacBg,
        shadowElevation = 6.dp,
        onClick = onClick,
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "추가",
            tint = lilac,
            modifier = Modifier
                .padding(16.dp)             // 아이콘 중앙 정렬 느낌
                .size(50.dp)
        )
    }
}




@Preview(showBackground = true)
@Composable
fun AddFloatingButtonPreview() {
    MaterialTheme {
        AddFloatingButton(
            onClick = {},
            modifier = Modifier
        )
    }
}
