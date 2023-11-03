package com.gunpang.domain.entity

import com.gunpang.common.code.DayCode

data class AvatarGoal(
    val sleepStart: String,
    val sleepEnd : String,
    val exerciseDay : List<DayCode>?,
    val exerciseTime : String,
    val foodGoal: String
){
    constructor() : this(
        "",
        "",
        null,
        "",
        "식사 기록하기"
    )
}
