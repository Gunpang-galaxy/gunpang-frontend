package com.gunpang.data.model.response

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