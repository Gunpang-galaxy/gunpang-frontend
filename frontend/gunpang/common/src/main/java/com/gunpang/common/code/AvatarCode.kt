package com.gunpang.common.code

import com.gunpang.common.R

enum class AvatarCode(
    val avatarTypeId: Int,
    val avatarDefaultName: String,
    val imageId: Int
) {
    AVATAR_CAT(avatarTypeId = 0, avatarDefaultName = "냥퍙이" ,imageId = R.drawable.avatar_cat),
    AVATAR_CHICK(avatarTypeId = 1, avatarDefaultName="짹팡이" ,imageId = R.drawable.avatar_chick),
}