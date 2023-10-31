package com.gunpang.common.code

import com.gunpang.common.R

enum class AvatarCode(
    val avatarDefaultName: String,
    val imageId: Int
) {
    AVATAR_CAT(avatarDefaultName = "냥퍙이" ,imageId = R.drawable.avatar_cat),
    AVATAR_CHICK(avatarDefaultName="짹팡이" ,imageId = R.drawable.avatar_chick),
}