package com.gunpang.data.model.response

import com.gunpang.common.code.MealRecordCode

data class TodayRecordResDto (
    val breakfastFoodType: MealRecordCode, // 아침 HEALTH, MEDIUM, UNHEALTH
    val lunchFoodType: MealRecordCode, // 점심
    val dinnerFoodType: MealRecordCode, // 저녁
    val exerciseTime: String, // 운동 시간(HH시간 MM분)
    val sleepTime: String   // 수면 시간 (HH시간 MM분)
)