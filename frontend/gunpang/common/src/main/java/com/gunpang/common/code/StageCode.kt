package com.gunpang.common.code

import com.gunpang.common.R

enum class StageCode(
    val level: Int,
    val imageId: Int,
) {
    SEA(level=1, imageId = R.drawable.background_sea),
    LAND(level=2, imageId = R.drawable.background_land),
    SKY(level=3, imageId = R.drawable.background_sky),
    SPACE(level=4, imageId = R.drawable.background_space),
    NOT_FOUND(level=-1, imageId = R.drawable.question_mark);
    companion object {
        fun fromString(stage: String): StageCode {
            var findResult : StageCode? = values().find { it.name == stage }
            return when {
                findResult != null -> findResult
                else -> NOT_FOUND
            }
        }
    }

}