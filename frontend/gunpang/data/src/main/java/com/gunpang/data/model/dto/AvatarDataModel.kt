package com.gunpang.data.model.dto

import com.gunpang.common.code.AvatarStatusCode
import com.gunpang.common.code.DeathCauseCode

// TODO: 배경 단계 이미지 추가 시 stage type을 enum 코드로 변경
data class AvatarDetailDto(
    val avatarType: Int,
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
    val avatarType: Int,
    val name: String, // 이름
    val stage: String, // 배경 단계
    val status: AvatarStatusCode, // 상태(생존/사망/졸업)
    val healthPoint: Float, // 체력
    val startedDate: String, // 시작 날짜
    val finishedDate: String, // 종료 날짜
)

