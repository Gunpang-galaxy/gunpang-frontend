package com.gunpang.data.model.request

import java.util.Date

data class SleepDataReqDto(
    val date: Date, // 오늘 날짜
    val sleepTime: Date, // 수면 시각
    val awakeTime: Date // 기상 시각
)