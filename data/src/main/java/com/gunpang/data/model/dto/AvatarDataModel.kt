package com.gunpang.data.model.dto

import com.gunpang.common.code.AvatarStatusCode
import com.gunpang.common.code.DeathCauseCode

// TODO: 배경 단계 이미지 추가 시 stage type을 enum 코드로 변경
data class AvatarDetailDto(
    val avatarTypeId: Int,
    val name: String, // 이름
    val stage: String, // 배경 단계
    val status: AvatarStatusCode, // 상태(생존/사망/졸업)
    val healthPoint: Int, // 체력
    val startedDate: String, // 시작 날짜
    val finishedDate: String, // 종료 날짜
    val deathCause: DeathCauseCode, // 사망 원인
)


data class CurrentAvatarInfoDto(
    val avatarId: Int,
    val avatarTypeId: Int,
    val name: String, // 이름
    val stage: String, // 배경 단계
    val status: AvatarStatusCode, // 상태(생존/사망/졸업)
    val healthPoint: Float, // 체력
    val startedDate: String, // 시작 날짜
    val finishedDate: String, // 종료 날짜
)

////// 메인화면 하단 상태에 따른 아바타 정보
data class AvatarAliveContent(
    val breakfastFoodType: String,
    val lunchFoodType: String,
    val dinnerFoodType: String,
    val exerciseTime : String,
    val sleepAt : String,
    val awakeAt : String
)

data class AvatarDeadContent(
    val deathCause: String // 사망 원인
)

data class AvatarGraduatedContent(
    val message: String // 졸업 축하 메시지
)