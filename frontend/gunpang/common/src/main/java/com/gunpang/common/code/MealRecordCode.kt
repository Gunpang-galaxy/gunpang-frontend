package com.gunpang.common.code

import com.gunpang.common.R

enum class MealRecordCode (
    val image: Int
){
    HEALTHY(R.drawable.food_healthy_salad),
    NORMAL(R.drawable.food_normal_rice),
    BAD(R.drawable.food_bad_fried),
    NOT_RECORD(R.drawable.nothing)
}