package com.gunpang.data.model.response

data class LoginResDto(
    val accessToken: String,
    val refreshToken: String
)

data class SignUpResDto(
    val googleId: String,
    val accessToken: String,
    val refreshToken: String
)

data class UserInfoResDto(
    val email : String,
    val gender : String,
    val birthYear : Int,
    val height : Int
)
