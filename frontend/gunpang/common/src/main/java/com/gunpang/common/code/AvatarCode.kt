package com.gunpang.common.code

import com.gunpang.common.R

enum class AvatarCode(
    val avatarDefaultName: String,
    val imageId: Int
) {
    AVATAR_CAT(avatarDefaultName = "냥팡이" ,imageId = R.drawable.avatar_cat),
    AVATAR_CHICK(avatarDefaultName="짹팡이" ,imageId = R.drawable.avatar_chick),

    AVATAR_NOT_FOUND(avatarDefaultName = "???", imageId = R.drawable.question_mark);

    // DB에서 들어온 String값으로 해당 ENUM 객체 반환
    // 찾을 수 없는 경우 AVATAR_NOT_FOUND 반환
    companion object {
        fun fromString(avatarType: String): AvatarCode {
            var findResult : AvatarCode? = values().find { it.name == avatarType }
            return when {
                findResult != null -> findResult
                else -> AVATAR_NOT_FOUND
            }
        }
    }
}