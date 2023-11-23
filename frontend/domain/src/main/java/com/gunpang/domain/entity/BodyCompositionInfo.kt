package com.gunpang.domain.entity

data class BodyCompositionInfo (
    val prevWeight: String,
    val curWeight: String,
    val prevFatMass: String,
    val curFatMass: String,
    val prevFatMassPct: String,
    val curFatMassPct: String,
    val prevBMI: String,
    val curBMI: String
    ) {
    constructor() : this(prevWeight = "", curWeight = "", prevFatMass = "", curFatMass = "", prevFatMassPct = "", curFatMassPct = "", prevBMI = "", curBMI = "")
}