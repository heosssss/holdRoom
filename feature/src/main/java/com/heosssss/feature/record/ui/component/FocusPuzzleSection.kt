package com.heosssss.feature.record.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heosssss.feature.record.model.PuzzleProgress

@Composable
fun FocusPuzzleSection(
    progress: PuzzleProgress,
    onClickUnlock: () -> Unit,
    modifier: Modifier = Modifier
){
    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "집중 퍼즐",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Normal)
        )
        if(progress.hasUnlockTicket){
            Button(
                onClick = onClickUnlock,
                modifier = Modifier
                    .padding(top = 3.dp)
                    .width(170.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9B8CFF))
            ){
                Text("퍼즐 조각 해제하기")
            }
        }else{
            OutlinedButton(
                onClick = {/* */},
                enabled = false,
                modifier = Modifier
                    .padding(top = 3.dp)
                    .width(170.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Color(0xFFE6E6E6))
            ) {
                Text("해제권 없음", color = Color(0xFF9A9A9A))
            }
        }

    }
    Spacer(Modifier.height(10.dp))

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 1.5.dp
    ) {
        Column(
            Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = "진행률",
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.ExtraLight),
                    color = Color.Gray)
                Text(
                    text = "${progress.unlockedPieces} / ${progress.totalPieces}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            PuzzleGrid(
                unlocked = progress.unlockedPieces,
                total = progress.totalPieces,
                modifier = Modifier.padding(top = 14.dp)
            )
        }
    }
}


@Composable
private fun PuzzleGrid(
    unlocked: Int,
    total: Int,
    modifier: Modifier = Modifier
){
    val size = 5
    Column(modifier) {
        var idx = 0
        repeat(size) {
            Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                repeat(size) {
                    idx += 1
                    val isUnlocked = idx <= unlocked
                    Surface(
                        modifier = Modifier.size(55.dp),
                        shape = RoundedCornerShape(10.dp),
                        color = if (isUnlocked) Color(0xFF9D4EDD) else Color(0xFFF1F1F1),
                        shadowElevation = 0.dp
                    ) {}
                }
            }
            if (it != size - 1) {
                Spacer(Modifier.size(3.dp))
            }
        }
    }
}





@Preview(
    name = "FocusPuzzleSection - Has Ticket",
    showBackground = true,
    backgroundColor = 0xFFF5F5F5,
    widthDp = 360
)
@Composable
private fun FocusPuzzleSectionPreview_HasTicket() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .background(Color(0xFFF5F5F5))
                .padding(16.dp)
        ) {
            FocusPuzzleSection(
                progress = PuzzleProgress(
                    unlockedPieces = 3,
                    totalPieces = 25,
                    hasUnlockTicket = true
                ),
                onClickUnlock = {}
            )
        }
    }
}

@Preview(
    name = "FocusPuzzleSection - No Ticket",
    showBackground = true,
    backgroundColor = 0xFFF5F5F5,
    widthDp = 360
)
@Composable
private fun FocusPuzzleSectionPreview_NoTicket() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .background(Color(0xFFF5F5F5))
                .padding(16.dp)
        ) {
            FocusPuzzleSection(
                progress = PuzzleProgress(
                    unlockedPieces = 5,
                    totalPieces = 25,
                    hasUnlockTicket = false
                ),
                onClickUnlock = {}
            )
        }
    }
}
