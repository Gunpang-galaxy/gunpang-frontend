package com.gunpang.data.model.response

data class LoginResDto(
    val accessToken: String,
    val refreshToken: String
)

data class SignUpResDto(
    val googleId: String
)