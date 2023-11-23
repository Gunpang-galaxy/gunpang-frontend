package com.gunpang.data.model.request

data class NameAvatarReqDto(
    val avatarType: String,
    val name: String
)

data class DetailAvatarReqDto(
    val avatarId: Int
)
