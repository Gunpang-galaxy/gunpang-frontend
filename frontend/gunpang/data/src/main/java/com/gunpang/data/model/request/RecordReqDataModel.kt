package com.gunpang.data.model.request

data class SleepDataReqDto(
    val sleepStartHour: Int,
    val sleepStartMinute: Int,
    val sleepEndHour: Int,
    val sleepEndMinute: Int
)