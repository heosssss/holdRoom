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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.heosssss.core_ui.component.BottomActionButton
import com.heosssss.core_ui.component.DayChip
import com.heosssss.core_ui.component.RepeatChip
import com.heosssss.core_ui.component.TimeField
import com.heosssss.core_ui.component.TimePickerDialog
import com.heosssss.domain.model.DayOfWeek
import com.heosssss.domain.model.RepeatType
import com.heosssss.domain.model.Time
import com.heosssss.feature.R
import com.heosssss.feature.notification.model.AppInfoUiModel
import com.heosssss.feature.notification.ui.component.AppList
import com.heosssss.feature.notification.ui.component.AppSearchBar
import com.heosssss.feature.notification.util.toResId
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

    val currentState = uiState

    if (currentState.showStartPicker && currentState.startTime != null) {
        TimePickerDialog(
            initialHour = currentState.startTime.hour,
            initialMinute = currentState.startTime.minute,
            onDismiss = { viewModel.onShowStartPicker(false) },
            onConfirm = { h, m ->
                viewModel.onStartTimeChanged(Time(h, m))
                viewModel.onShowStartPicker(false)
            }
        )
    }

    if (currentState.showEndPicker && currentState.endTime != null) {
        TimePickerDialog(
            initialHour = currentState.endTime.hour,
            initialMinute = currentState.endTime.minute,
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
                text = stringResource(R.string.notif_btn_save),
                onClick = { viewModel.saveNotificaion(appListUiState.selectedApps.toList()) },
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.surface,
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
                    startTime = uiState.startTime ?: Time(0, 0),
                    endTime = uiState.endTime ?: Time(0, 0),
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
                if (currentState.repeatType != null) {
                    RepeatSelectionSection(
                        repeatMode = currentState.repeatType, // Smart Cast로 인해 Non-null로 인식
                        selectedDays = currentState.selectedDays,
                        onModeClick = { mode, days -> viewModel.onRepeatModeChanged(mode, days) },
                        onDayClick = { day -> viewModel.onDaySelected(day) }
                    )
                }
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
            text = stringResource(R.string.notif_title_ask_time),
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 24.dp)
        )
        Text(
            text = stringResource(R.string.notif_title_time),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.outlineVariant)
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = title,
            onValueChange = onNameChange,
            modifier = Modifier.fillMaxWidth().height(52.dp),
            placeholder = { Text(text = stringResource(R.string.notif_placeholder_title_time)) },
            singleLine = true,
            shape = RoundedCornerShape(16.dp)
        )
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun TimeSettingSection(
    startTime: Time,
    endTime: Time,
    onStartClick: () -> Unit,
    onEndClick: () -> Unit
) {
    Column {
        Text(
            text = stringResource(R.string.notif_time_setting),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.outlineVariant
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.notif_time_start),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.outlineVariant
        )
        Spacer(Modifier.height(6.dp))
        TimeField(
            time = startTime.toDisplayString(),
            onClick = onStartClick
        )
        Spacer(Modifier.height(16.dp))
        Text(
            stringResource(R.string.notif_time_end),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.outlineVariant)
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
    apps: List<AppInfoUiModel>,
    selectedApps: Set<String>,
    onQueryChange: (String) -> Unit,
    onAppToggle: (String) -> Unit
) {
    Column {
        Text(
            text = stringResource(R.string.notif_help_focus),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = stringResource(R.string.notif_app_block),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.outlineVariant,
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
    selectedDays: Set<DayOfWeek>,
    onModeClick: (RepeatType, Set<DayOfWeek>) -> Unit,
    onDayClick: (DayOfWeek) -> Unit
) {
    Column {
        Text(
            text =stringResource(R.string.notif_repeat),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.outlineVariant
        )
        Spacer(Modifier.height(12.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            RepeatChip(
                text = "주중",
                selected = repeatMode == RepeatType.WEEKLY,
                onClick = {
                    onModeClick(RepeatType.WEEKLY, setOf(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY))
                }
            )
            RepeatChip(
                text = "주말",
                selected = repeatMode == RepeatType.WEEkEND,
                onClick = {
                    onModeClick(RepeatType.WEEkEND, setOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)) }
            )
            RepeatChip(
                text = "매일",
                selected = repeatMode == RepeatType.DAILY,
                onClick = { onModeClick(RepeatType.DAILY, DayOfWeek.entries.toSet())}
            )
        }
        Spacer(Modifier.height(16.dp))
        val days = DayOfWeek.entries
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            days.forEach { day ->
                DayChip(
                    text = stringResource(id = day.toResId()),
                    selected = selectedDays.contains(day),
                    onClick = { onDayClick(day) }
                )
            }
        }
        Spacer(Modifier.height(80.dp))
    }
}


