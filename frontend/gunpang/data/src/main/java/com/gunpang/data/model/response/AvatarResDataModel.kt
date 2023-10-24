package com.gunpang.data.model.response

import com.gunpang.data.model.dto.*

data class RandomAvatarResDto(
    val avatarType: Int,
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
