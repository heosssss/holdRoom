package com.heosssss.feature.record.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heosssss.feature.record.model.BlockedAppItem
import com.heosssss.feature.record.model.PuzzleProgress
import com.heosssss.feature.record.model.WeeklyRecordSummary
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecordViewModel(
    // TODO: Clean Architecture 연결 시 UseCase들을 여기 주입 (Hilt 사용해라 ~)
//    private val recordRepository: RecordRepository
) : ViewModel() { //viewModel상속받았음. 화면 회전이나 시스템 변화에도 상태를 유지해줌요

    //상태 관리
    // 내부용. 값을 바꿀 수 있는 setter 역할
    private val _uiState = MutableStateFlow(RecordUiState(isLoading = true))
    // 외부용. 값을 읽을 수만 있는 getter 역할
    val uiState = _uiState.asStateFlow()

    //생성자 실행(side effect) : 클래스가 생성될때 init블록이 가장 먼저 실행. useEffect()=>{},[] 같은거네
    init {
        load()
    }

    private fun load() {
        //async와 비슷한 개념. 비동기 작업을 시작(코루틴)
        viewModelScope.launch {
            //todo. 실제 데이터 로딩으로 교체 필요(usecase라던가 ~)
            _uiState.update { // 리액트로 치면 setUiState(prevState => ({ ... }))와 완전 동일 상태 set하는 것
                // 코틀린의 data class는 기본적으로 불변이기 때문에 스프레드연산자(...state)처럼 기존 객체를 it 복사해서 값이 바뀐 새 객체를 만드는 것임
                it.copy(
                    isLoading = false,
                    weekly = WeeklyRecordSummary(totalFocusMinutes = 400, focusSessions = 12),
                    puzzle = PuzzleProgress(unlockedPieces = 3, totalPieces = 25, hasUnlockTicket = false),
                    blockedTop3 = listOf(
                        BlockedAppItem("Instagram", 18),
                        BlockedAppItem("YouTube", 11),
                        BlockedAppItem("X", 7)
                    ),
                    reflectionMessage = "happy인거에요"
                    )
            }
        }
    }


    fun onClickUnlockPuzzlePiece(){
        _uiState.update { state -> // 현재 상태를 갖고와요
            val p = state.puzzle
            //
            if(!p.hasUnlockTicket) return@update state  //return 할때 어떤 블록을 종료하는지 명시해줌 여기서는 @update 블록을 종료
            if(p.unlockedPieces >= p.totalPieces) return@update state

            state.copy(
                puzzle = p.copy(
                    unlockedPieces = p.unlockedPieces + 1,
                    hasUnlockTicket = false
                )
            )
        }
    }


}