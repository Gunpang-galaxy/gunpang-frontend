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
    val finishedDate: String?,
) {
    constructor() : this(
        AvatarCode.AVATAR_CAT,
        AvatarCode.AVATAR_CAT.avatarDefaultName,
        AvatarStatusCode.ALIVE,
        StageCode.NOT_FOUND,
        0f,
        "",
        null,
    )
}
