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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.heosssss.core_ui.component.BottomActionButton
import com.heosssss.core_ui.component.DayChip
import com.heosssss.core_ui.component.RepeatChip
import com.heosssss.core_ui.component.TimeField
import com.heosssss.core_ui.component.TimePickerDialog
import com.heosssss.domain.model.RepeatType
import com.heosssss.domain.model.Time
import com.heosssss.feature.notification.model.AppInfoUiModel
import com.heosssss.feature.notification.ui.component.AppList
import com.heosssss.feature.notification.ui.component.AppSearchBar
import com.heosssss.feature.notification.viewmodel.AddTimeNotificationViewModel
import com.heosssss.feature.notification.viewmodel.AppListViewModel

@SuppressLint("DefaultLocale")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTimeNotification(
    onBackClick: () -> Unit = {},
    viewModel: AddTimeNotificationViewModel = hiltViewModel(),
    appListViewModel: AppListViewModel = hiltViewModel()
    ) {

    //ViewModel들로부터 상태 수집
    val uiState by viewModel.uiState.collectAsState()
    val appListUiState by appListViewModel.uiState.collectAsState()

    //시작 시간 다이얼로그
    if (uiState.showStartPicker) {
        TimePickerDialog(
            initialHour = uiState.startTime.hour,
            initialMinute = uiState.startTime.minute,
            onDismiss = { viewModel.onShowStartPicker(false) },
            onConfirm = { h, m ->
                viewModel.onStartTimeChanged(Time(h, m))
                viewModel.onShowStartPicker(false)
            }
        )
    }
    //종료 시간 다이얼로그
    if (uiState.showEndPicker) {
        TimePickerDialog(
            initialHour = uiState.endTime.hour,
            initialMinute = uiState.endTime.minute,
            onDismiss = { viewModel.onShowEndPicker(false) },
            onConfirm = { h, m ->
                viewModel.onEndTimeChanged(Time(h, m))
                viewModel.onShowEndPicker(false)
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
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 8.dp)
        ) {
            item {
                NameSection(
                    title = uiState.title,
                    onNameChange = viewModel::onTitleChanged
                )
            }
            item {
                TimeSettingSection(
                    startTime = uiState.startTime,
                    endTime = uiState.endTime,
                    onStartClick = { viewModel.onShowStartPicker(true) },
                    onEndClick = { viewModel.onShowEndPicker(true) }
                )
            }
            item {
                AppSelectionSection(
                    query = appListUiState.searchQuery,
                    apps = appListUiState.apps,
                    selectedApps = appListUiState.selectedApps,
                    onQueryChange = appListViewModel::onSearchQueryChange,
                    onAppToggle = appListViewModel::toggleApp
                )
            }
            item {
                RepeatSelectionSection(
                    repeatMode = uiState.repeatType,
                    selectedDays = uiState.selectedDaysString, // 요일 처리는 ViewModel에서!
                    onModeClick = { mode, days -> viewModel.onRepeatModeChanged(mode) },
                    onDayClick = { day -> viewModel.onDaySelected(day) }
                )
            }
        }
    }
}


// 상단 타이틀 & 이름 입력 섹션
@Composable
private fun NameSection(
    title: String,
    onNameChange: (String) -> Unit
) {
    Column {
        Text(
            text = "무슨 시간이에요?",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 24.dp)
        )
        Text(text = "시간 이름", style = MaterialTheme.typography.labelMedium, color = Color.Gray)
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = title,
            onValueChange = onNameChange,
            modifier = Modifier.fillMaxWidth().height(52.dp),
            placeholder = { Text(text = "예: 아침 집중, 자기 전에") },
            singleLine = true,
            shape = RoundedCornerShape(16.dp)
        )
        Spacer(Modifier.height(24.dp))
    }
}

// 시간 설정 섹션 (시작/종료 시간)
@Composable
private fun TimeSettingSection(
    startTime: Time,
    endTime: Time,
    onStartClick: () -> Unit,
    onEndClick: () -> Unit
) {
    Column {
        Text(text = "시간 설정", style = MaterialTheme.typography.labelMedium, color = Color.Gray)
        Spacer(Modifier.height(12.dp))
        Text(text = "시작 시간", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
        Spacer(Modifier.height(6.dp))
        TimeField(
            time = startTime.toDisplayString(),
            onClick = onStartClick
        )
        Spacer(Modifier.height(16.dp))
        Text(text = "종료 시간", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
        Spacer(Modifier.height(6.dp))
        TimeField(
            time = endTime.toDisplayString(),
            onClick = onEndClick
        )
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun AppSelectionSection(
    query: String,
    apps: List<AppInfoUiModel>, // AppListViewModel에서 가져온 데이터
    selectedApps: Set<String>,
    onQueryChange: (String) -> Unit,
    onAppToggle: (String) -> Unit
) {
    Column {
        Text(
            text = "집중을 도와드릴게요.",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "어떤 앱들은 집중을 방해할 수 있어요.",
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF8E8E93),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.height(12.dp))
        AppSearchBar(
            query = query,
            onQueryChange = onQueryChange
        )
        AppList(
            installedApps = apps,
            selectedApps = selectedApps,
            onAppToggle = onAppToggle
        )
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun RepeatSelectionSection(
    repeatMode: RepeatType,
    selectedDays: Set<String>,
    onModeClick: (RepeatType, Set<String>) -> Unit,
    onDayClick: (String) -> Unit
) {
    Column {
        Text(text = "반복할까요?", style = MaterialTheme.typography.labelMedium, color = Color.Gray)
        Spacer(Modifier.height(12.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            RepeatChip(
                text = "주중",
                selected = repeatMode == RepeatType.WEEKLY,
                onClick = { onModeClick(RepeatType.WEEKLY, setOf("월", "화", "수", "목", "금")) }
            )
            RepeatChip(
                text = "주말",
                selected = repeatMode == RepeatType.WEEkEND,
                onClick = { onModeClick(RepeatType.WEEkEND, setOf("토", "일")) }
            )
            RepeatChip(
                text = "매일",
                selected = repeatMode == RepeatType.DAILY, // RepeatType에 DAILY가 있다고 가정
                onClick = { onModeClick(RepeatType.DAILY, setOf("월", "화", "수", "목", "금", "토", "일")) }
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
                    onClick = { onDayClick(day) }
                )
            }
        }
        Spacer(Modifier.height(80.dp))
    }
}


