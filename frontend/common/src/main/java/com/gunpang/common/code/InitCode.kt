package com.gunpang.common.code

enum class InitCode(val description:String) {
    NOT_FOUND("기기 없음"),
    NOT_INSTALL("앱 설치 안됨"),
    NOT_LOGIN("로그인 필요"),
    REGISTER("회원가입 필요"),
    NO_GOAL_SET("목표 설정 안함"),
    AVATAR_NOT_CREATED("아바타 생성 안되어 있음"),
    COMPOSITION_NOT_SET("체지방 측정 하지 않음"),
    NOT_CONFIG("그외 설정 안됨"),
    FINISH("모든 설정 완료")
}