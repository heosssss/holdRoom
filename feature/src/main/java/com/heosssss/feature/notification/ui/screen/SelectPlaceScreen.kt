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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.FloatingActionButton
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
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState

//import com.naver.maps.geometry.LatLng
//import com.naver.maps.map.CameraPosition
//import com.naver.maps.map.compose.*

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SelectPlaceScreen(
//    onBackClick: () -> Unit = {},
//    onConfirmClick: (String) -> Unit ={}
//){
//    //todo. 지도에 선택된 주소 상태 (일단은 더미로)
//    var mainAddress by remember { mutableStateOf("서울특별시 강남구") }
//    var subAddress by remember { mutableStateOf("역삼동 123-45") }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "장소 선택") },
//                navigationIcon = {
//                    IconButton(onClick = onBackClick) {
//                        Icon(
//                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                            contentDescription = "뒤로가기"
//                        )
//                    }
//                }
//            )
//        }
//    ) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .padding(innerPadding)
//                .fillMaxSize()
//        ){
//            OutlinedTextField(
//                value = "",
//                onValueChange = {/* todo 검색 기능 추가 할 때 */},
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 24.dp, vertical = 12.dp),
//                leadingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.Search,
//                        contentDescription = null,
//                        tint = Color(0xFFB0B0B5)
//                    )
//                },
//                placeholder = { Text(text = "장소를 검색해주세요") },
//                singleLine = true,
//                enabled = false,
//                shape = RoundedCornerShape(24.dp),
//                colors = OutlinedTextFieldDefaults.colors(
//                    disabledBorderColor = Color.Transparent,
//                    disabledContainerColor = Color(0xFFF5F5F7),
//                    disabledTextColor = Color.Unspecified,
//                    disabledPlaceholderColor = Color(0xFFB0B0B5))
//            )
//        }
//        /* todo 추후 지도 기능 붙이면 */
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color(0xFFF5F5F7))
//        ){
//            /* todo 추후 지도 기능 붙이면 실제 지도 영역 자리가 여기임!!!!*/
//            Column(
//                modifier = Modifier
//                    .align(Alignment.CenterEnd)
//                    .padding(end = 24.dp),
//                verticalArrangement = Arrangement.spacedBy(12.dp)
//            ) {
//                Surface(
//                    shape = CircleShape,
//                    shadowElevation = 4.dp,
//                    color = Color.White
//                ) {
//                    IconButton(onClick = { /* TODO: 현재 위치 */ }) {
//                        Icon(
//                            imageVector = Icons.Default.MyLocation,
//                            contentDescription = "현재 위치",
//                            tint = Color(0xFF666666)
//                        )
//                    }
//                }
//                Surface(
//                    shape = CircleShape,
//                    shadowElevation = 4.dp,
//                    color = Color.White
//                ) {
//                    IconButton(onClick = { /* TODO: 확대 */ }) {
//                        Icon(
//                            imageVector = Icons.Default.Add,
//                            contentDescription = "확대",
//                            tint = Color(0xFF666666)
//                        )
//                    }
//                }
//                Surface(
//                    shape = CircleShape,
//                    shadowElevation = 4.dp,
//                    color = Color.White
//                ) {
//                    IconButton(onClick = { /* TODO: 축소 */ }) {
//                        Icon(
//                            imageVector = Icons.Default.Remove,
//                            contentDescription = "축소",
//                            tint = Color(0xFF666666)
//                        )
//                    }
//                }
//            }
//
//            // ----- 아래 바텀 카드 영역 ----- todo. 이것도 추후 실제 지도 달면 변경 (임시임)
//            Surface(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .align(Alignment.BottomCenter),
//                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
//                color = Color.White,
//                shadowElevation = 16.dp
//            ) {
//                Column(
//                    modifier = Modifier
//                        .padding(horizontal = 24.dp, vertical = 20.dp)
//                        .navigationBarsPadding()
//                ) {
//                    // 상단 핸들 바
//                    Box(
//                        modifier = Modifier
//                            .align(Alignment.CenterHorizontally)
//                            .width(40.dp)
//                            .height(4.dp)
//                            .background(
//                                color = Color(0xFFE0E0E0),
//                                shape = RoundedCornerShape(50)
//                            )
//                    )
//
//                    Spacer(Modifier.height(16.dp))
//
//                    Text(
//                        text = "선택한 위치",
//                        fontSize = 12.sp,
//                        color = Color(0xFF8E8E93)
//                    )
//
//                    Spacer(Modifier.height(8.dp))
//
//                    Row(
//                        verticalAlignment = Alignment.Top
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.LocationOn,
//                            contentDescription = null,
//                            tint = Color(0xFF9B8CFF)
//                        )
//                        Spacer(Modifier.width(8.dp))
//                        Column {
//                            Text(
//                                text = mainAddress,
//                                fontSize = 16.sp,
//                                fontWeight = FontWeight.SemiBold
//                            )
//                            Spacer(Modifier.height(4.dp))
//                            Text(
//                                text = subAddress,
//                                fontSize = 13.sp,
//                                color = Color(0xFF8E8E93)
//                            )
//                        }
//                    }
//
//                    Spacer(Modifier.height(20.dp))
//
//                    Button(
//                        onClick = { onConfirmClick(mainAddress) },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(52.dp),
//                        shape = RoundedCornerShape(26.dp),
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = Color(0xFF9B8CFF)
//                        )
//                    ) {
//                        Text(
//                            text = "이 장소로 설정하기 ✔",
//                            fontSize = 15.sp,
//                            fontWeight = FontWeight.SemiBold
//                        )
//                    }
//                }
//            }
//        }
//    }
//}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalNaverMapApi::class)
@Composable
fun SelectPlaceScreen(
    onBackClick: () -> Unit,
    onConfirmClick: (LatLng) -> Unit
) {
    // 1. 카메라 상태 관리 (초기 위치: 서울시청)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition(LatLng(37.5666102, 126.9783881), 15.0)
    }

    // 현재 중앙 좌표를 실시간으로 추적
    val currentCenter = cameraPositionState.position.target

    val uiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                isLocationButtonEnabled = true,
                isZoomControlEnabled = false,
                isLogoClickEnabled = true
            )
        )
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("차단 장소 선택", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "뒤로가기")
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {

            // 2. 네이버 지도 표시
            NaverMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                uiSettings =  uiSettings // 수정된 부분
            ) {
                // 여기에 필요시 마커나 원을 그릴 수 있지만,
                // "선택" 단계에서는 화면 중앙에 고정된 UI를 그리는 게 더 편합니다.
            }

            // 3. 중앙 고정 가이드 (20m 반경 느낌의 원)
            // 지도가 움직여도 이 원과 핀은 화면 중앙에 고정됩니다.
            Box(
                modifier = Modifier
                    .size(120.dp) // 대략적인 20m 범위를 나타내는 원
                    .background(Color(0x229B8CFF), CircleShape) // 연한 보라색 배경
                    .align(Alignment.Center)
            )

            // 4. 중앙 고정 핀 아이콘
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = Color(0xFF9B8CFF),
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.Center)
                    .offset(y = (-20).dp) // 핀의 뾰족한 끝이 정중앙에 오도록 보정
            )

            // 5. 내 위치 버튼
            FloatingActionButton(
                onClick = { /* TODO: 위치 권한 획득 후 현재 위치로 카메라 이동 */ },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.dp, bottom = 220.dp),
                containerColor = Color.White,
                shape = CircleShape
            ) {
                Icon(Icons.Default.MyLocation, contentDescription = "내 위치", tint = Color.Black)
            }

            // 6. 하단 정보 카드
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                color = Color.White,
                shadowElevation = 10.dp
            ) {
                Column(
                    modifier = Modifier.padding(24.dp).navigationBarsPadding()
                ) {
                    Text("선택된 좌표", fontSize = 12.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(4.dp))

                    // 실시간으로 변하는 위도/경도 표시
                    Text(
                        text = "위도: ${String.format("%.5f", currentCenter.latitude)}\n" +
                                "경도: ${String.format("%.5f", currentCenter.longitude)}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = { onConfirmClick(currentCenter) },
                        modifier = Modifier.fillMaxWidth().height(54.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9B8CFF))
                    ) {
                        Text("이 장소 등록하기", fontSize = 16.sp, color = Color.White)
                    }
                }
            }
        }
    }
}
