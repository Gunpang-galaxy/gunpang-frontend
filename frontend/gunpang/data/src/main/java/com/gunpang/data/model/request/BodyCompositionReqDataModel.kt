package com.gunpang.data.model.request

data class BodyCompositionApiReqDto(
    val weight: Double, // 체중[kg]
    val fatMassPct: Double, // 체지방률[%]
)