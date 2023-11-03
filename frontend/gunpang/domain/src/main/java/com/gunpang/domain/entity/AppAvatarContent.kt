package com.gunpang.domain.entity

import com.gunpang.common.code.DeathCauseCode
import com.gunpang.common.code.MealRecordCode

data class AppAvatarAliveContent(
    val breakfastFoodType: MealRecordCode,
    val lunchFoodType: MealRecordCode,
    val dinnerFoodType: MealRecordCode,
    val exerciseTime: String,
    val sleepAt: String,
    val awakeAt: String,
){
    constructor() : this(
        breakfastFoodType = MealRecordCode.NOT_RECORD,
        lunchFoodType = MealRecordCode.NOT_RECORD,
        dinnerFoodType = MealRecordCode.NOT_RECORD,
        exerciseTime = "00시간00분",
        sleepAt = "-1",
        awakeAt = "-1",
    )
}

data class AppAvatarDeadContent(
    val deathCause: DeathCauseCode
) {
    constructor() : this(
        deathCause = DeathCauseCode.SLEEP_BROKEN
    )
}

data class AppAvatarGraduatedContent(
    val message: String
){
    constructor() : this(
        message = "축하해요"
    )
}