package com.gunpang.data.model.response

data class BodyCompositionResDto(
    val prevWeight: Double, // 한 달 전 체중[kg]
    val curWeight: Double, // 현재 체중[kg]
    val prevMuscleMass: Double, // 한 달 전 골격근량[kg]
    val curMuscleMass: Double, // 현재 골격근량[kg]
    val prevFatMass: Double, // 한 달 전 체지방량[kg]
    val curFatMass: Double, // 현재 체지방량[kg]
    val prevFatMassPct: Double, // 한 달 전 체지방률[%]
    val curFatMassPct: Double, // 현재 체지방률[%]
    val prevBMI: Double, // 한 달 전 BMI
    val curBMI: Double, // 현재 BMI
    val prevBodyWaterMass: Double, // 한 달 전 체수분량[kg]
    val curBodyWaterMass: Double, // 현재 체수분량[kg]
)