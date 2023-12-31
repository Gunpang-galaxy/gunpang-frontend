package com.gunpang.domain.entity

import com.gunpang.common.code.DayCode

data class AvatarGoal(
    val sleepStart: String,
    val sleepEnd : String,
    val exerciseDay : List<DayCode>,
    val exerciseTime : String,
    val foodGoal: String
){
    constructor() : this(
        sleepStart="",
        sleepEnd="",
        exerciseDay=listOf(),
        exerciseTime="00시간 00분",
        foodGoal="건강한 식사 하기"
    )
}
