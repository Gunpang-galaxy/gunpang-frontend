package com.gunpang.data.model.response

import com.gunpang.common.code.ExerciseIntensityCode
import com.gunpang.common.code.MealRecordCode

data class ExerciseResDto(
    val exerciseAccTime: String,
    val exerciseIntensity: ExerciseIntensityCode
)

data class CalendarRecordResDto (
    val breakfastFoodType: MealRecordCode, // 아침 HEALTHY, NORMAL, BAD, NOT_RECORD
    val lunchFoodType: MealRecordCode, // 점심
    val dinnerFoodType: MealRecordCode, // 저녁
    val exerciseTime: String, // 운동 시간(HH시간 MM분)
    val sleepAt: String,   // 수면 시간 (HH시간 MM분)
    val awakeAt: String, //깬 시간(HH시간 MM분)
    val exercisesOnDate : List<ExerciseResDto>//리스트 안에 dto...
)