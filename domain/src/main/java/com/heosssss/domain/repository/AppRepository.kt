package com.heosssss.domain.repository

import com.heosssss.domain.model.AppPackageInfo

interface AppRepository {
    fun getInstalledApps(): List<AppPackageInfo>
}