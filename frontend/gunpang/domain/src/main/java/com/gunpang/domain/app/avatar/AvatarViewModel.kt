package com.gunpang.domain.app.avatar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gunpang.common.code.AvatarCode
import com.gunpang.common.code.AvatarStatusCode
import com.gunpang.common.code.DayCode
import com.gunpang.common.code.DeathCauseCode
import com.gunpang.common.code.MealRecordCode
import com.gunpang.common.code.StageCode
import com.gunpang.data.model.response.AppAvatarInfoResDto
import com.gunpang.data.repository.AvatarRepository
import com.gunpang.domain.entity.AppAvatar
import com.gunpang.domain.entity.AppAvatarAliveContent
import com.gunpang.domain.entity.AppAvatarDeadContent
import com.gunpang.domain.entity.AppAvatarGraduatedContent
import com.gunpang.domain.entity.AvatarGoal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class AvatarViewModel : ViewModel(){

    var avatarGoal by mutableStateOf<AvatarGoal>(AvatarGoal()) // 해당 아바타 목표
    var appAvatar by mutableStateOf<AppAvatar>(AppAvatar()) // 아바타 정보
    var avatarAliveContents by mutableStateOf(AppAvatarAliveContent()) // 현재 아바타 컨텐츠
    var avatarDeadContents by mutableStateOf(AppAvatarDeadContent()) // 현재 아바타 컨텐츠
    var avatarGraduatedContents by mutableStateOf(AppAvatarGraduatedContent()) // 현재 아바타 컨텐츠

    var prevId by mutableIntStateOf(-1) // 이전 아바타 아이디
    var nextId by mutableIntStateOf(-1)  // 다음 아바타 아이디

    var currentAvatarExist : Boolean = true ;

    private val avatarRepository : AvatarRepository = AvatarRepository() // 아바타 관련 Repository

    //현재 아바타 정보 가져오기
    private fun getCurrentAvatar() {
        viewModelScope.launch(Dispatchers.IO) {
            avatarRepository.getAvatarCurrentInfo()
                .catch {
                    currentAvatarExist = false
                    it.printStackTrace()
                }
                .collect {data ->
                    avatarGoal = AvatarGoal(
                        data.goal.sleepStart,
                        data.goal.sleepEnd,
                        DayCode.fromBitCount(data.goal.exerciseDay),
                        data.goal.exerciseTime,
                        "건강한 음식 먹기"
                    )
                    appAvatar = AppAvatar(
                        AvatarCode.fromString(data.avatarType),
                        data.name,
                        AvatarStatusCode.fromString(data.status),
                        StageCode.fromString(data.stage),
                        data.healthPoint,
                        data.startedDate,
                        data.finishedDate
                    )
                    when {
                        data.status == "GRAUDATED" -> {
                            avatarGraduatedContents = AppAvatarGraduatedContent(
                                data.contents["exerciseTotal"] as Int,
                                data.contents["exerciseSuccessCnt"] as Int,
                                data.contents["foodTotal"] as Int,
                                data.contents["foodSuccessCnt"] as Int,
                                data.contents["sleepTotal"] as Int,
                                data.contents["sleepSuccessCnt"] as Int
                            )
                        }
                        data.status == "DEAD" -> {
                            avatarDeadContents = AppAvatarDeadContent(
                                DeathCauseCode.fromString(data.contents["deathCause"].toString())
                            )
                        }
                        else ->
                            avatarAliveContents = AppAvatarAliveContent(
                                MealRecordCode.fromString(data.contents["breakfastFoodType"].toString()),
                                MealRecordCode.fromString(data.contents["breakfastFoodType"].toString()),
                                MealRecordCode.fromString(data.contents["breakfastFoodType"].toString()),
                                data.contents["exerciseTime"].toString(),
                                data.contents["sleepAt"].toString(),
                                data.contents["awakeAt"].toString()
                            )
                    }
                    prevId = data.hasPrev
                    nextId = data.hasNext
                }
        }
    }

    // 아바타 아이디를 통한 이전, 다음 아바타 보기
    fun getAvatar(avatarId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            avatarRepository.getAvatarInfo(avatarId)
                .catch {
                    it.printStackTrace()
                }
                .collect {data ->
                    avatarGoal = AvatarGoal(
                        data.goal.sleepStart,
                        data.goal.sleepEnd,
                        DayCode.fromBitCount(data.goal.exerciseDay),
                        data.goal.exerciseTime,
                        "건강한 음식 먹기"
                    )
                    appAvatar = AppAvatar(
                        AvatarCode.fromString(data.avatarType),
                        data.name,
                        AvatarStatusCode.fromString(data.status),
                        StageCode.fromString(data.stage),
                        data.healthPoint,
                        data.startedDate,
                        data.finishedDate
                    )
                    when {
                        data.status == "GRAUDATED" -> {
                            avatarGraduatedContents = AppAvatarGraduatedContent(
                                data.contents["exerciseTotal"] as Int,
                                data.contents["exerciseSuccessCnt"] as Int,
                                data.contents["foodTotal"] as Int,
                                data.contents["foodSuccessCnt"] as Int,
                                data.contents["sleepTotal"] as Int,
                                data.contents["sleepSuccessCnt"] as Int
                            )
                        }
                        data.status == "DEAD" -> {
                            avatarDeadContents = AppAvatarDeadContent(
                                DeathCauseCode.fromString(data.contents["deathCause"].toString())
                            )
                        }
                        else ->
                            avatarAliveContents = AppAvatarAliveContent(
                                MealRecordCode.fromString(data.contents["breakfastFoodType"].toString()),
                                MealRecordCode.fromString(data.contents["lunchFoodType"].toString()),
                                MealRecordCode.fromString(data.contents["dinnerFoodType"].toString()),
                                data.contents["exerciseTime"].toString(),
                                data.contents["sleepAt"].toString(),
                                data.contents["awakeAt"].toString()
                            )
                    }
                    prevId = data.hasPrev
                    nextId = data.hasNext
                }
        }
    }
    // 아바타 타입으로
    // 메인화면 진입시 호출
    fun init(){
        getCurrentAvatar()
    }
}