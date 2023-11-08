package com.gunpang.common.code

enum class DeathCauseCode (
    val status: String
) {
    EXERCISE_LACK("운동 부족"),
    FOOD_LACK("식사 안함"),
    SLEEP_BROKEN("수면 패턴 망가짐"),
    NOT_FOUND("사망 원인 없음");

    companion object {
        fun fromString(deathCause: String): DeathCauseCode {
            var findResult : DeathCauseCode? = values().find { it.name == deathCause }
            return when {
                findResult != null -> findResult
                else -> NOT_FOUND
            }
        }
    }
}