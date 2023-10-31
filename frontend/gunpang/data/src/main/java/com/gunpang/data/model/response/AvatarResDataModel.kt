package com.gunpang.data.model.response

import com.gunpang.common.code.AvatarCode
import com.gunpang.common.code.AvatarStatusCode
import com.gunpang.data.model.dto.*

data class RandomAvatarResDto(
    val avatarTypeId: Int,
    val name: String, // 아바타 default name
)

data class DetailAvatarResDto(
    val avatar: AvatarDetailDto,
    val hasPrev: Int, // 이전 아바타 id, 없으면 -1
    val hasNext: Int, // 다음 아바타 id, 없으면 -1
)

data class CurrentAvatarResDto(
    val goal : AvatarGoalResDto, // 아바타 목표
    val avatarType: String, // 아바타 타입
    val name: String, // 아바타 이름
    val stage: String, // 배경 단계
    val status: String, // 상태(생존/사망/졸업)
    val healthPoint: Float, // 체력
    val startedDate: String, // 시작 날짜
    val finishedDate: String?, // 아바타 마지막 생존 날짜
    val hasPrev: Int, // 이전 아바타 id, 없으면 -1
    val hasNext: Int, // 다음 아바타 id, 없으면 -1
    val contents: Map<String, Any> // 아바타 상세 정보
)

data class WatchCurrentAvatarResDto(
    val avatarTypeId: AvatarCode,
    val stage: String, // 배경 단계
    val status: AvatarStatusCode, // 상태(생존/사망/졸업)
    val healthPoint: Float, // 체력
)