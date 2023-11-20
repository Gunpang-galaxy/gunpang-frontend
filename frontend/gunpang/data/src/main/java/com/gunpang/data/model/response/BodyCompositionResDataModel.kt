package com.gunpang.data.model.response

data class BodyCompositionInfoResDto(
    val prevWeight: String, // 한 달 전 체중[kg]
    val curWeight: String, // 현재 체중[kg]
    val prevFatMass: String, // 한 달 전 체지방량[kg]
    val curFatMass: String, // 현재 체지방량[kg]
    val prevFatMassPct: String, // 한 달 전 체지방률[%]
    val curFatMassPct: String, // 현재 체지방률[%]
    val prevBMI: String, // 한 달 전 BMI
    val curBMI: String // 현재 BMI
)