package com.gunpang.common.code

import com.gunpang.common.R

enum class MealRecordCode (
    val foodType: String,
    val imageId: Int
){
    HEALTHY(foodType="건강한 음식", imageId = R.drawable.food_healthy_salad),
    NORMAL(foodType="적당한 음식", imageId = R.drawable.food_normal_rice),
    BAD(foodType="나쁜 음식", imageId = R.drawable.food_bad_fried),
    NOT_RECORD(foodType="음식 기록 안함", imageId = R.drawable.nothing)
}