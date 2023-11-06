package com.gunpang.common.code

enum class InitCode(val description:String) {
    NOT_FOUND("기기 없음"),
    NOT_INSTALL("앱 설치 안됨"),
    NOT_LOGIN("로그인 필요"),
    REGISTER("회원가입 필요"),
    NOT_CONFIG("그외 설정 안됨"),
    FINISH("모든 설정 완료")
}