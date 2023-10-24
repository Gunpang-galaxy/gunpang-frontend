package com.gunpang.data.model.request

data class SleepGoalReqDto(
    val avatarId: Int, // 아바타 아이디
    val startTime: Int, // 목표 취침 시간
    val startMinute: Int, // 목표 취침 분
    val endTime: Int, // 목표 기상 시간
    val endMinute: Int // 목표 기상 분
)
data class ExerciseGoalReqDto(
    val avatarId: Int, // 아바타 아이디
    val exerciseDay: Int, // 목표 운동 요일(비트마스킹)
    val exerciseTime: Int // 목표 운동 시간[분]
)

data class MonthlyGaolReqDto(
    val year: Int, // 조회 연도
    val month: Int // 조회 월
)

data class DailyGoalReqDto(
    val year: Int,
    val month: Int,
    val date: Int
)