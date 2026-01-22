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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.heosssss.core_ui.component.BottomActionButton
import com.heosssss.feature.R

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
                text = stringResource(R.string.notif_title_ask_place),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Text(
                text = stringResource(R.string.notif_title_place),
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = placeName,
                onValueChange = {placeName = it},
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = stringResource(R.string.notif_placeholder_title_place))
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(Modifier.height(24.dp))

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
//            AppList()
            Spacer(Modifier.height(80.dp))
        }
    }

}