package com.gunpang.data.model.dto

data class AvatarGoalResDto(
    val sleepStart : String, // 목표 수면 시간
    val sleepEnd : String, // 목표 기상 시간
    val exerciseDay : Int, // 목표 운동 요일
    val exerciseTime : Int, // 목표 운동 시간
    val food : String // 건강한 음식 챙겨 먹기
)