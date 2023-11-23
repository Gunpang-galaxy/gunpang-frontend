package com.gunpang.domain.entity

import com.gunpang.common.code.AvatarCode
import com.gunpang.common.code.AvatarStatusCode
import com.gunpang.common.code.StageCode

data class AppAvatar(
    val avatarType: AvatarCode,
    val avatarName: String,
    val status: AvatarStatusCode,
    val stage: StageCode,
    val healthPoint: Float,
    val startedDate: String,
    val finishedDate: String,
) {
    constructor() : this(
        avatarType=AvatarCode.AVATAR_NOT_FOUND,
        avatarName=AvatarCode.AVATAR_NOT_FOUND.avatarDefaultName,
        status = AvatarStatusCode.ALIVE,
        stage = StageCode.NOT_FOUND,
        healthPoint=0f,
        startedDate="",
        finishedDate="",
    )
}
