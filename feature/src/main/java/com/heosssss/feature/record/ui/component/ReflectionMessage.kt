package com.heosssss.feature.record.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
@Composable
fun ReflectionMessage(message: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = message,
        style = MaterialTheme.typography.bodyMedium,
        color = Color(0xFF7A7A7A),
        textAlign = TextAlign.Center
    )
}



@Preview(
    name = "ReflectionMessage",
    showBackground = true,
    backgroundColor = 0xFFF5F5F5,
    widthDp = 360
)
@Composable
private fun ReflectionMessagePreview() {
    MaterialTheme {
        ReflectionMessage(
            message = "오늘도 한 걸음"
        )
    }
}
