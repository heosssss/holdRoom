package com.heosssss.feature.notification.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectPlaceScreen(
    onBackClick: () -> Unit = {},
    onConfirmClick: (String) -> Unit ={}
){
    //todo. 지도에 선택된 주소 상태 (일단은 더미로)
    var mainAddress by remember { mutableStateOf("서울특별시 강남구") }
    var subAddress by remember { mutableStateOf("역삼동 123-45") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "장소 선택") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "뒤로가기"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ){
            OutlinedTextField(
                value = "",
                onValueChange = {/* todo 검색 기능 추가 할 때 */},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = Color(0xFFB0B0B5)
                    )
                },
                placeholder = { Text(text = "장소를 검색해주세요") },
                singleLine = true,
                enabled = false,
                shape = RoundedCornerShape(24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    disabledBorderColor = Color.Transparent,
                    disabledContainerColor = Color(0xFFF5F5F7),
                    disabledTextColor = Color.Unspecified,
                    disabledPlaceholderColor = Color(0xFFB0B0B5))
            )
        }
        /* todo 추후 지도 기능 붙이면 */
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F7))
        ){
            /* todo 추후 지도 기능 붙이면 실제 지도 영역 자리가 여기임!!!!*/
            Column(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Surface(
                    shape = CircleShape,
                    shadowElevation = 4.dp,
                    color = Color.White
                ) {
                    IconButton(onClick = { /* TODO: 현재 위치 */ }) {
                        Icon(
                            imageVector = Icons.Default.MyLocation,
                            contentDescription = "현재 위치",
                            tint = Color(0xFF666666)
                        )
                    }
                }
                Surface(
                    shape = CircleShape,
                    shadowElevation = 4.dp,
                    color = Color.White
                ) {
                    IconButton(onClick = { /* TODO: 확대 */ }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "확대",
                            tint = Color(0xFF666666)
                        )
                    }
                }
                Surface(
                    shape = CircleShape,
                    shadowElevation = 4.dp,
                    color = Color.White
                ) {
                    IconButton(onClick = { /* TODO: 축소 */ }) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = "축소",
                            tint = Color(0xFF666666)
                        )
                    }
                }
            }

            // ----- 아래 바텀 카드 영역 ----- todo. 이것도 추후 실제 지도 달면 변경 (임시임)
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                color = Color.White,
                shadowElevation = 16.dp
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 20.dp)
                        .navigationBarsPadding()
                ) {
                    // 상단 핸들 바
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(40.dp)
                            .height(4.dp)
                            .background(
                                color = Color(0xFFE0E0E0),
                                shape = RoundedCornerShape(50)
                            )
                    )

                    Spacer(Modifier.height(16.dp))

                    Text(
                        text = "선택한 위치",
                        fontSize = 12.sp,
                        color = Color(0xFF8E8E93)
                    )

                    Spacer(Modifier.height(8.dp))

                    Row(
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = Color(0xFF9B8CFF)
                        )
                        Spacer(Modifier.width(8.dp))
                        Column {
                            Text(
                                text = mainAddress,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = subAddress,
                                fontSize = 13.sp,
                                color = Color(0xFF8E8E93)
                            )
                        }
                    }

                    Spacer(Modifier.height(20.dp))

                    Button(
                        onClick = { onConfirmClick(mainAddress) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(26.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF9B8CFF)
                        )
                    ) {
                        Text(
                            text = "이 장소로 설정하기 ✔",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}
