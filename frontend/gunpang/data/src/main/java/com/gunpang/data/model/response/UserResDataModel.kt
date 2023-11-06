package com.gunpang.data.model.response

import com.gunpang.common.code.GenderCode

data class LoginResDto(
    val accessToken: String,
    val refreshToken: String
)

data class PersonalInfoResDto(
    val email: String,
    val gender: GenderCode,
    val birthYear: Int,
    val height: Int
)