package com.gunpang.data.model.request

data class MonthlyCompositionReqDto(
    val weight: Double, // 체중[kg]
    val muscleMass: Double, // 골격근량[kg]
    val fatMass: Double, // 체지방량[kg]
    val bodyWaterMass: Double, // 체수분량[kg]
)