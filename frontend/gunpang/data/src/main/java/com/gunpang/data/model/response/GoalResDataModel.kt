package com.gunpang.data.model.response

data class AvatarGoalResDto(
    val sleepStart: String, // 목표 취침 시간
    val sleepEnd: String, // 목표 기상 시간
    val exerciseDay: Int, // 목표 운동 요일
    val exerciseTime: String, // 목표 운동 시간 (??시간??분)
    val food: String // 음식 목표
)

// api 요청 후 List<MontHlyGoalResDto> 형태로 받아옴
data class MonthlyGoalResDtos(
    var data : List<MonthlyGoalResDto>,
)

data class MonthlyGoalResDto(
    val date: Int, // 조회 일
    val record: Int, // 미달성 개수
)
