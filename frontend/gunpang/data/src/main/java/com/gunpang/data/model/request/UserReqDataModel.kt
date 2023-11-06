package com.gunpang.data.model.request

data class LoginReqDto(
    val googleId: String
)

data class SignUpReqDto(
    val gender: String,
    val birthYear: Int, // 출생 연도
    val height: Int, // 키[cm]
    val googleId: String,
    val email: String
)