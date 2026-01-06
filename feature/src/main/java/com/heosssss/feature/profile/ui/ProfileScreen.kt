package com.heosssss.feature.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heosssss.core_ui.component.AppScreenScaffold
import com.heosssss.core_ui.component.BottomActionButton
import com.heosssss.feature.profile.model.ProfileMenuItem
import com.heosssss.feature.profile.model.defaultProfileMenus
import com.heosssss.feature.profile.theme.ProfileColors

@Composable
fun ProfileScreen(
    userName: String = "슬기",
    menus: List<ProfileMenuItem> = defaultProfileMenus(),
    onHeaderClick: () -> Unit = {},
    onMenuClick: (ProfileMenuItem) -> Unit = {},
    onLogout: () -> Unit = {},
    onDeleteAccount: () -> Unit = {},
){
    AppScreenScaffold(title = "프로필"){ innerPadding ->
        Column(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ){

            Spacer((Modifier.height(20.dp)))

            ProfileHeaderCard(
                userName = userName,
                onClick = onHeaderClick
            )

            Spacer(Modifier.height(24.dp))

            ProfileMenuSection(
                menus = menus,
                onMenuClick = onMenuClick
            )

            //버튼 하단에 고정
            Spacer(modifier = Modifier.weight(1f))

            BottomActionButton(
                text = "로그아웃",
                onClick = onLogout,
                containerColor = ProfileColors.PastelBg,
                contentColor = ProfileColors.VioletJelly
            )

            Spacer(Modifier.height(10.dp))

            BottomActionButton(
                text = "계정삭제",
                onClick = onDeleteAccount,
                containerColor = Color.Red,
                contentColor = Color.White
            )


            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview(
    name = "ProfileScreen",
    showBackground = true,
    widthDp = 360,
    heightDp = 800
)
@Composable
private fun ProfileScreenPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
        ) {
            ProfileScreen(
                userName = "슬기",
                menus = defaultProfileMenus(),
                onHeaderClick = {},
                onMenuClick = {},
                onLogout = {},
                onDeleteAccount = {}
            )
        }
    }
}