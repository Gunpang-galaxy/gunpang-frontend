package com.gunpang.data.model.response

import com.gunpang.common.code.GenderCode

data class LoginResDto(
    val accessToken: String,
    val refreshToken: String
)

data class SignUpResDto(
    val googleId: String
)

data class UserInfoResDto(
    val email : String,
    val gender : String,
    val birth : Int,
    val height : Int
)
data class PersonalInfoResDto(
    val email: String,
    val gender: GenderCode,
    val birthYear: Int,
    val height: Int
)