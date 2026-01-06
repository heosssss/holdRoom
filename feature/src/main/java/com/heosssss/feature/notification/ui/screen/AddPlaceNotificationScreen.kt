package com.heosssss.feature.notification.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.heosssss.core_ui.component.AppToggleRow
import com.heosssss.core_ui.component.BottomActionButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPlaceNotification(
    onBackClick: () -> Unit = {},
    onClickSelectOnMap: () -> Unit = {},
    onClickSave: (
            placeName : String,
            enabledApps: List<String>,
            ) -> Unit = {_, _ ->},
){
    var placeName by remember { mutableStateOf("") }

    //todo. 임시 상태
    var instagram by remember { mutableStateOf(true) }
    var twitter by remember { mutableStateOf(false) }
    var youtube by remember { mutableStateOf(true) }
    var facebook by remember { mutableStateOf(false) }
    var spotify by remember { mutableStateOf(false) }
    var game by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "뒤로가기"
                        )
                    }
                },
//                windowInsets = WindowInsets(0.dp)
            )
        },
        bottomBar = {
            BottomActionButton(
                text = "이렇게 할래요",
                onClick = {/*todo*/},
                containerColor = Color(0xFF9B8CFF),
                contentColor = Color.White
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 8.dp)
        ) {
            Text(
                text = "어디인가요?",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Text(
                text = "장소 이름",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = placeName,
                onValueChange = {placeName = it},
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "예: 집, 회사, 학교")
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(Modifier.height(24.dp))

            Text(
                text = "장소",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )
            Spacer(Modifier.height(8.dp))

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFFF5F5F7),
                onClick = onClickSelectOnMap
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            modifier = Modifier.size(40.dp),
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFFE5F0FF)
                        ) { }

                        Spacer(Modifier.width(12.dp))

                        Text(
                            text = "지도에서 선택하기",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }

                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = null,
                        tint = Color(0xFFB0B0B5)
                    )
                }
            }

            Spacer(Modifier.height(32.dp))

            Text(
                text = "집중을 도와드릴게요.",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "어떤 앱들은 이 장소에서 집중을 방해할 수 있어요.",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF8E8E93),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(24.dp))

            //todo. 앱 토글 임시로 그림만 그려놓음
            AppToggleRow(
                name = "Instagram",
                gradientColors = listOf(
                    Color(0xFFFFC36B),
                    Color(0xFFE1306C)
                ),
                enabled = instagram,
                onEnabledChange = { instagram = it }
            )

            AppToggleRow(
                name = "Twitter",
                solidColor = Color(0xFF1DA1F2),
                enabled = twitter,
                onEnabledChange = { twitter = it }
            )

            AppToggleRow(
                name = "YouTube",
                solidColor = Color(0xFFFF0000),
                enabled = youtube,
                onEnabledChange = { youtube = it }
            )
            AppToggleRow(
                name = "Instagram",
                gradientColors = listOf(
                    Color(0xFFFFC36B),
                    Color(0xFFE1306C)
                ),
                enabled = instagram,
                onEnabledChange = { instagram = it }
            )

            AppToggleRow(
                name = "Twitter",
                solidColor = Color(0xFF1DA1F2),
                enabled = twitter,
                onEnabledChange = { twitter = it }
            )

            AppToggleRow(
                name = "YouTube",
                solidColor = Color(0xFFFF0000),
                enabled = youtube,
                onEnabledChange = { youtube = it }
            )

            Spacer(Modifier.height(80.dp))

        }
    }

}