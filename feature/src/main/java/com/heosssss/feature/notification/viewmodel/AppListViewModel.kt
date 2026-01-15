package com.heosssss.feature.notification.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heosssss.domain.repository.AppRepository
import com.heosssss.feature.notification.model.AppInfoUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppListViewModel @Inject constructor(
    private val appRepository: AppRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val packageManager = context.packageManager

    // 기본 상태 선언
    private val _allApps = MutableStateFlow<List<AppInfoUiModel>>(emptyList())
    private val _searchQuery = MutableStateFlow("")
    private val _selectedApps = MutableStateFlow<Set<String>>(emptySet())

    // UI에 전달할 상태
    val uiState: StateFlow<AppListUiState> = combine(  //항상 현재 상태를 갖고있음. combine안에서 하나라도 값이 바뀌면 블록 다시 실행함
        _allApps, _searchQuery, _selectedApps
    ) { apps, query, selected ->
        val filtered = if (query.isBlank()) apps
                    else apps.filter { it.name.contains(query, ignoreCase = true) }

        val sorted = filtered.sortedWith(
            compareByDescending<AppInfoUiModel> { selected.contains(it.packageName) }
                .thenBy { it.name }
        )
        AppListUiState(
            apps = sorted,
            searchQuery = query,
            selectedApps = selected
        )
    }.stateIn(  //일반 flow를 stateflow로 변환
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000), //화면이 보일때만 계산, 화면 나가면 5초뒤 계산 멈춤
        initialValue = AppListUiState()
    )

    init {
        loadInstalledApps()
    }

    //사용자의 의도 처리 함수
    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }
    fun toggleApp(packageName: String) {
        _selectedApps.value = if(_selectedApps.value.contains(packageName)) {
            _selectedApps.value - packageName
        } else {
            _selectedApps.value + packageName
        }
    }

    //내부 로직
    private fun loadInstalledApps(){
        viewModelScope.launch {
            val apps = appRepository.getInstalledApps().map { domain ->
                AppInfoUiModel(
                    name = domain.name,
                    packageName = domain.packageName,
                    icon = try {
                        packageManager.getApplicationIcon(domain.packageName)
                    } catch (e: Exception) {
                        null
                    }
                )
            }
            _allApps.value = apps
        }
    }

    data class AppListUiState(
        val apps: List<AppInfoUiModel> = emptyList(),
        val searchQuery: String = "",
        val selectedApps: Set<String> = emptySet()
    )
}