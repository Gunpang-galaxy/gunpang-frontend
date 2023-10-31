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
    val avatar: CurrentAvatarInfoDto,
    val hasPrev: Int, // 이전 아바타 id, 없으면 -1
)

data class WatchCurrentAvatarResDto(
    val avatarTypeId: AvatarCode,
    val stage: String, // 배경 단계
    val status: AvatarStatusCode, // 상태(생존/사망/졸업)
    val healthPoint: Float, // 체력
)