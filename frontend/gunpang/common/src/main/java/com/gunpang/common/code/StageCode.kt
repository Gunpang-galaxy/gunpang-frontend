package com.gunpang.common.code

import com.gunpang.common.R

enum class StageCode(
    level: Int,
    imageId: Int,
) {
    SEA(level=1, imageId = R.drawable.background_sea),
    LAND(level=2, imageId = R.drawable.background_land),
    SKY(level=3, imageId = R.drawable.background_sky),
    SPACE(level=4, imageId = R.drawable.background_space)

}