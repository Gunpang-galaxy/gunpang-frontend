package com.gunpang.data.model.request

import java.time.Instant

data class SleepDataReqDto(
    val sleepStartHour: Int,
    val sleepStartMinute: Int,
    val sleepEndHour: Int,
    val sleepEndMinute: Int
)
data class SleepHealthConnectReqDto(
    val recordDate: String,
    val sleepAt: String,
    val awakeAt:String,
)
