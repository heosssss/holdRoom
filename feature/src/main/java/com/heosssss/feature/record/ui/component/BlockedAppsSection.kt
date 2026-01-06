package com.heosssss.feature.record.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heosssss.feature.record.model.BlockedAppItem

@Composable
fun BlockedAppsSection(
    top3: List<BlockedAppItem>,
    modifier: Modifier = Modifier
){

    Text(
        text = "가장 많이 차단된 앱이에요",
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

            if(top3.isEmpty()){
                Text(
                    text = "아직 차단된 앱이 없어요. 이제부터 시작해볼까요?",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF7A7A7A)
                )
                // @Column 블록을 빠져나가려고 레이블을 붙여놓은것임 Column 밑에 다른 UI가 있다면 그것은 그려짐
                return@Column
            }

            top3.take(3).forEachIndexed { index, item ->
                Row(Modifier.fillMaxWidth()) {
                    Text(text = item.appName, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                    Spacer(Modifier.weight(1f))
                    Text(text = "${item.count}회", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                }
                if(index != top3.lastIndex && index != 2){
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 20.dp),
                        color = Color(0xFFEDEDED),
                        thickness = 1.dp
                    )
                }
            }
        }
    }
}


@Preview(
    name = "BlockedAppsSection - With Data",
    showBackground = true,
    backgroundColor = 0xFFF5F5F5,
    widthDp = 360
)
@Composable
private fun BlockedAppsSectionPreview_WithData() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .background(Color(0xFFF5F5F5))
                .padding(16.dp)
        ) {
            BlockedAppsSection(
                top3 = listOf(
                    BlockedAppItem(appName = "Instagram", count = 18),
                    BlockedAppItem(appName = "YouTube", count = 11),
                    BlockedAppItem(appName = "X", count = 7)
                )
            )
        }
    }
}

@Preview(
    name = "BlockedAppsSection - Empty",
    showBackground = true,
    backgroundColor = 0xFFF5F5F5,
    widthDp = 360
)
@Composable
private fun BlockedAppsSectionPreview_Empty() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .background(Color(0xFFF5F5F5))
                .padding(16.dp)
        ) {
            BlockedAppsSection(
                top3 = emptyList()
            )
        }
    }
}
