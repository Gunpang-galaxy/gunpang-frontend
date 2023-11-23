package com.gunpang.data.model.request

import com.gunpang.common.code.MealRecordCode
import com.gunpang.common.code.TimeToEatCode

data class FoodReqDto(
    val foodType: MealRecordCode,
)