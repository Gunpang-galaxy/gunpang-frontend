package com.gunpang.domain.entity

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.gunpang.common.code.GenderCode

data class UserInfo(
    val email: String,
    val gender : GenderCode,
    val birth : Int,
    val height : Int
){
    constructor() : this(email ="", gender = GenderCode.NOT_FOUND, birth = 0, height = 0)
}
