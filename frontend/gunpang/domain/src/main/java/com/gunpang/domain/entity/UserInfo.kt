package com.gunpang.domain.entity

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.gunpang.common.code.GenderCode

data class UserInfo(
    val email: String,
    val gender : GenderCode?,
    val birth : Int,
    val height : Int
){
    constructor() : this(
        "",
        null,
        0,
        0
    )
}
