package com.gunpang.data.model.response

data class TodayRecordResDto (
    val breakfastFoodType: String, // 아침 HEALTH, MEDIUM, UNHEALTH
    val lunchFoodType: String, // 점심
    val dinnerFoodType: String, // 저녁
    val exerciseTime: String, // 운동 시간(HH:MM:SS)
    val sleepAt: String, // 잠든 시각 "2023-10-26T23:00"
    val awakeAt: String // 깬 시각 "2023-10-26T23:00"
)