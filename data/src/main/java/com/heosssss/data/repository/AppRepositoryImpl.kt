package com.heosssss.data.repository

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.heosssss.domain.model.AppPackageInfo
import com.heosssss.domain.repository.AppRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : AppRepository {
    override fun getInstalledApps(): List<AppPackageInfo> {
        val packageManager = context.packageManager

        // 1. 모든 애플리케이션 정보를 가져옴
        val allApps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)

        return allApps.filter { appInfo ->
            // 2. '실행 가능한 인텐트'가 있는지 확인 (유튜브 같은 앱을 확실히 잡기 위함)
            val launchIntent = packageManager.getLaunchIntentForPackage(appInfo.packageName)

            // 3. 필터링 조건
            // - 실행 인텐트가 있어야 함 (아이콘이 있다는 뜻)
            // - 혹은 시스템 앱이 아니어야 함 (사용자가 설치한 앱)
            // - 업데이트된 시스템 앱이어야 함 (유튜브 등 구글 기본 앱)

            launchIntent != null && (
                    (appInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0) || // 사용자가 직접 설치한 앱
                            (appInfo.flags and ApplicationInfo.FLAG_UPDATED_SYSTEM_APP != 0) || // 업데이트된 시스템 앱
                            // 기본 시스템 앱 중에서 런처 아이콘이 있는 경우 포함
                            isImportantSystemApp(appInfo.packageName)
                    )
        }
            .map { appInfo ->
                AppPackageInfo(
                    name = appInfo.loadLabel(packageManager).toString(),
                    packageName = appInfo.packageName,
                )
            }
            .filter { it.packageName != context.packageName } // 내 앱 제외
            .distinctBy { it.packageName }
            .sortedBy { it.name }
    }

    // 필수로 포함시키고 싶은 패키지가 있다면 추가 (선택사항)
    private fun isImportantSystemApp(packageName: String): Boolean {
        val importantApps = listOf("com.google.android.youtube", "com.android.chrome", "com.google.android.apps.maps")
        return importantApps.contains(packageName)
    }
}
