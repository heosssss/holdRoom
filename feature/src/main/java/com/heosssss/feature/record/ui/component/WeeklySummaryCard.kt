package com.heosssss.feature.record.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WeeklySummaryCard(
    totalFocusMinutes: Int,
    focusSessions: Int,
    modifier: Modifier = Modifier
){
    Text(
        text = "이번 주에는 이만큼 집중했어요",
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Normal)
    )
    Spacer(Modifier.height(10.dp))

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 1.5.dp
    ) {
        Column(Modifier.padding(16.dp)) {
//            Text(
//                text = "이번 주 기록",
//                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
//            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                StatBlock(
                    label = "총 집중 시간",
                    value = formatMinutes(totalFocusMinutes)
                )

                VerticalDivider(
                    modifier = Modifier.height(52.dp),
                    color = Color(0xFFEDEDED))

                StatBlock(
                    label = "집중 세션",
                    value = "${focusSessions}회"
                )
            }
        }
    }

}


@Composable
private fun StatBlock(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, style = MaterialTheme.typography.bodySmall, color = Color(0xFF7A7A7A))
        Text(
            text = buildAnnotatedString {
                value.forEach { char ->
                    if (char.isDigit()) {
                        // 숫자만 보라색
                        withStyle(
                            SpanStyle(
                                color = Color(0xFF9D4EDD),
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append(char)
                        }
                    } else {
                        append(char)
                    }
                }
            },
            style = MaterialTheme.typography.titleLarge
        )
    }
}

private fun formatMinutes(min: Int): String {
    val h = min / 60
    val m = min % 60
    return if (h <= 0) "${m}분" else "${h}시간 ${m}분"
}





@Preview(
    name = "WeeklySummaryCard",
    showBackground = true,
    backgroundColor = 0xFFF5F5F5,
    widthDp = 360
)
@Composable
private fun WeeklySummaryCardPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .background(Color(0xFFF5F5F5))
                .padding(16.dp)
        ) {
            WeeklySummaryCard(
                totalFocusMinutes = 400, // 6시간 40분
                focusSessions = 12
            )
        }
    }
}