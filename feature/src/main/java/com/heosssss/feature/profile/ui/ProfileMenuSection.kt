package com.heosssss.feature.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heosssss.feature.profile.model.ProfileMenuId
import com.heosssss.feature.profile.model.ProfileMenuItem

@Composable
fun ProfileMenuSection(
    menus: List<ProfileMenuItem>,
    onMenuClick : (ProfileMenuItem) -> Unit,
    modifier: Modifier = Modifier
){
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        color = Color.White,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp
    ) {
        Column {
            menus.forEachIndexed { index, item ->
                ProfileMenuRow(
                    title = item.title,
                    onClick = { onMenuClick(item) }
                )
                if (index != menus.lastIndex){
                    HorizontalDivider(color = Color(0xFFEDEDED), thickness = 1.dp)
                }
            }
        }
    }
}


@Composable
private fun ProfileMenuRow(
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(73.dp)
            .clickable { onClick() }
            .padding(horizontal = 23.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.weight(1f))
        Text(text = "›", style = MaterialTheme.typography.titleLarge, color = Color(0xFF9A9A9A))
    }
}


@Preview(
    name = "ProfileMenuSection",
    showBackground = true,
    backgroundColor = 0xFFDDDDDD // 좀 더 진하게
)
@Composable
private fun ProfileMenuSectionPreview() {
    val menus = listOf(
        ProfileMenuItem(title = "기능설정", id = ProfileMenuId.SETTINGS),
        ProfileMenuItem(title = "프로필", id = ProfileMenuId.EDIT_PROFILE),
        ProfileMenuItem(title = "신규공지사항", id = ProfileMenuId.ANNOUNCEMENTS)
    )

    MaterialTheme {
        androidx.compose.foundation.layout.Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFDDDDDD))
                .padding(16.dp)
        ) {
            ProfileMenuSection(
                menus = menus,
                onMenuClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)
            )
        }
    }
}
