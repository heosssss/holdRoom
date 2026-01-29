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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.heosssss.core_ui.component.BottomActionButton
import com.heosssss.feature.R
import com.heosssss.feature.notification.model.AppInfoUiModel
import com.heosssss.feature.notification.ui.component.AppList
import com.heosssss.feature.notification.ui.component.AppSearchBar
import com.heosssss.feature.notification.viewmodel.AddPlaceNotificationViewModel
import com.heosssss.feature.notification.viewmodel.AppListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPlaceNotification(
    onBackClick: () -> Unit = {},
    onClickSelectOnMap: () -> Unit = {},
    onNavigateBack: () -> Unit,
    viewModel: AddPlaceNotificationViewModel = hiltViewModel(),
    appListViewModel: AppListViewModel = hiltViewModel(),
){
    val uiState by viewModel.uiState.collectAsState()
    val appListUiState by appListViewModel.uiState.collectAsState()

    val currentState = uiState
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.blockedApps) {
        if (uiState.blockedApps.isNotEmpty()) {
            appListViewModel.setSelectedApps(uiState.blockedApps)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.saveSuccessEvent.collect {
            snackbarHostState.showSnackbar("저장되었습니다.")
            onNavigateBack()
        }
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
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 8.dp)
        ) {
            item{
                NameSection(
                    title = uiState.title,
                    onNameChange = viewModel::onTitleChanged
                )
            }
            item{
                PlaceSettingSection(onClickSelectOnMap = onClickSelectOnMap)
            }
            item{
                AppSelectionSection(
                    query = appListUiState.searchQuery,
                    apps = appListUiState.apps,
                    selectedApps = appListUiState.selectedApps,
                    onQueryChange = appListViewModel::onSearchQueryChange,
                    onAppToggle = appListViewModel::toggleApp
                )
            }
        }
    }

}

@Composable
private fun NameSection(
    title: String,
    onNameChange: (String) -> Unit
) {
    var placeName by remember { mutableStateOf("") }
    Column {
        Text(
                text = stringResource(R.string.notif_title_ask_place),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Text(
                text = stringResource(R.string.notif_title_place),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outlineVariant
            )
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = title,
                onValueChange = onNameChange,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                placeholder = {
                    Text(text = stringResource(R.string.notif_placeholder_title_place))
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp)
            )
            Spacer(Modifier.height(24.dp))
    }
}


@Composable
private fun PlaceSettingSection(onClickSelectOnMap: () -> Unit) {
    Column {
                Text(
                text = stringResource(R.string.notif_place),
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
                            text = stringResource(R.string.notif_btn_select_on_map),
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