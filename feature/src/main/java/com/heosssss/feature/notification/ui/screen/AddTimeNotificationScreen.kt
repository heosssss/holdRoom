package com.heosssss.feature.notification.ui.screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.heosssss.core_ui.component.AppToggleRow
import com.heosssss.core_ui.component.BottomActionButton
import com.heosssss.core_ui.component.DayChip
import com.heosssss.core_ui.component.RepeatChip
import com.heosssss.core_ui.component.TimeField
import com.heosssss.core_ui.component.TimePickerDialog
import com.heosssss.domain.model.Time
import com.heosssss.feature.notification.model.RepeatMode
import com.heosssss.feature.notification.viewmodel.AddTimeNotificationViewModel
import java.time.LocalTime
import java.time.format.DateTimeFormatter




@RequiresApi(Build.VERSION_CODES.O)
private val hmFormatter = DateTimeFormatter.ofPattern("HH:mm")
@RequiresApi(Build.VERSION_CODES.O)
private fun LocalTime.toHmString(): String = format(hmFormatter)

@SuppressLint("DefaultLocale")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTimeNotification(
    onBackClick: () -> Unit = {},
    onSavedClick: () -> Unit = {},
    viewModel: AddTimeNotificationViewModel = hiltViewModel() //뷰 모델 주입
) {

    //다이얼로그 표시 여부
    var showStartPicker by remember { mutableStateOf(false) }
    var showEndPicker by remember { mutableStateOf(false) }

    // 앱 리스트 상태
    //todo. 현재는 임시로 그려둔 상태임. 추후 실제 앱 리스트 갖고오면 변경 필요
    var isInstagramEnabled by remember { mutableStateOf(true) }
    var isFacebookEnabled by remember { mutableStateOf(true) }
    var isYoutubeEnabled by remember { mutableStateOf(false) }
    var isTwitterEnabled by remember { mutableStateOf(false) }
    var isTiktokEnabled by remember { mutableStateOf(true) }

    //반복 옵션 상태
    var repeatMode by remember { mutableStateOf(RepeatMode.Everyday) }
    var selectedDays by remember {
        mutableStateOf(
            mutableSetOf("월", "화", "수", "목", "금", "토", "일")
        )
    }

    //시작 시간 다이얼로그
    if(showStartPicker){
        TimePickerDialog(
            initialHour = viewModel.startTime.hour,
            initialMinute = viewModel.startTime.minute,
            onDismiss = { showStartPicker = false },
            onConfirm = { hour, minute ->
                viewModel.onStartTimeChanged(Time(hour, minute))
                showStartPicker = false
            }
        )
    }

    //종료 시간 다이얼로그
    if(showEndPicker){
        TimePickerDialog(
            initialHour = viewModel.endTime.hour,
            initialMinute = viewModel.endTime.minute,
            onDismiss = { showEndPicker = false },
            onConfirm = { hour, minute ->
                viewModel.onEndTimeChanged(Time(hour, minute))
                showEndPicker = false
            }
        )
    }


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
            )
        },
        bottomBar = {
            BottomActionButton(
                text = "이렇게 할래요",
                onClick = { viewModel.saveNotificaion() },
                containerColor = Color(0xFF9B8CFF),
                contentColor = Color.White
            )
        },
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 8.dp)
        ) {
            Text(
                text = "무슨 시간이에요?",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Text(
                text = "시간 이름",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = viewModel.timeName,
                onValueChange = {viewModel.onTimeNameChanged(it)},
                modifier = Modifier
                    .fillMaxSize()
                    .height(52.dp),
                placeholder = {
                    Text(text ="예: 아침 집중, 자기 전에")
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp)
            )
            Spacer(Modifier.height(24.dp))

            //시간 설정
            Text(
                text = "시간 설정",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )
            Spacer(Modifier.height(12.dp))

            Text(
                text = "시작 시간",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )
            Spacer(Modifier.height(6.dp))
            TimeField(
                time = String.format("%02d:%02d", viewModel.startTime.hour, viewModel.startTime.minute),
                onClick = { showStartPicker = true }
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "종료 시간",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )
            Spacer(Modifier.height(6.dp))
            TimeField(
                time = String.format("%02d:%02d", viewModel.endTime.hour, viewModel.startTime.minute),
                onClick = { showEndPicker = true }
            )
            Spacer(Modifier.height(24.dp))



            // 차단할 앱 목록
            //todo 차단할 앱 목록을 어떻게 갖고 오나요 ~
            Text(
                text = "집중을 도와드릴게요.",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "어떤 앱들은 이 시간동안 집중을 방해할 수 있어요.",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF8E8E93),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(12.dp))
            AppToggleRow(
                name = "Instagram",
                gradientColors = listOf(Color(0xFFFF9A9E), Color(0xFFFAD0C4)),
                enabled = isInstagramEnabled,
                onEnabledChange = { isInstagramEnabled = it }
            )
            AppToggleRow(
                name = "Facebook",
                gradientColors = listOf(Color(0xFF4C6FFF), Color(0xFF89A7FF)),
                enabled = isFacebookEnabled,
                onEnabledChange = { isFacebookEnabled = it }
            )
            AppToggleRow(
                name = "YouTube",
                solidColor = Color(0xFFFF5252),
                enabled = isYoutubeEnabled,
                onEnabledChange = { isYoutubeEnabled = it }
            )
            AppToggleRow(
                name = "Twitter",
                solidColor = Color(0xFF1DA1F2),
                enabled = isTwitterEnabled,
                onEnabledChange = { isTwitterEnabled = it }
            )
            AppToggleRow(
                name = "TikTok",
                gradientColors = listOf(Color(0xFFFFA726), Color(0xFFFFCC80)),
                enabled = isTiktokEnabled,
                onEnabledChange = { isTiktokEnabled = it }
            )

            //todo. 여기까지 임시로 앱 리스트 그려준 것임. 추후 변경 필요



            Spacer(Modifier.height(24.dp))

            //반복 여부
            Text(
                text = "반복할까요?",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )
            Spacer(Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                RepeatChip(
                    text = "주중",
                    selected = repeatMode == RepeatMode.Weekdays,
                    onClick = {
                        repeatMode = RepeatMode.Weekdays
                        selectedDays = mutableSetOf("월", "화", "수", "목", "금")
                    }
                )
                RepeatChip(
                    text = "주말",
                    selected = repeatMode == RepeatMode.Weekend,
                    onClick = {
                        repeatMode = RepeatMode.Weekend
                        selectedDays = mutableSetOf("토", "일")
                    }
                )
                RepeatChip(
                    text = "매일",
                    selected = repeatMode == RepeatMode.Everyday,
                    onClick = {
                        repeatMode = RepeatMode.Everyday
                        selectedDays = mutableSetOf("월", "화", "수", "목", "금", "토", "일")
                    }
                )
            }

                Spacer(Modifier.height(16.dp))

                val days = listOf("월", "화", "수", "목", "금", "토", "일")

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    days.forEach { day ->
                        DayChip(
                            text = day,
                            selected = selectedDays.contains(day),
                            onClick = {
                                val newSet = selectedDays.toMutableSet()
                                if(newSet.contains(day)) newSet.remove(day) else newSet.add(day)
                                selectedDays = newSet

                                repeatMode = RepeatMode.Custom
                            }
                        )
                    }
                }

                Spacer(Modifier.height(80.dp))
            }

        }

    }



