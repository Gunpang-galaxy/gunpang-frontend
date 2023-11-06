package com.gunpang.common.code

enum class GenderCode(
    val kor: String,
    val engUppercase: String
) {
    MALE("남성", "MALE"),
    FEMALE("여성", "FEMALE"),
    NOT_FOUND("성별 정보를 찾을 수 없음", "NOT_FOUND");
    companion object {
        fun fromString(gender: String): GenderCode {
            var findResult : GenderCode ? = GenderCode.values().find { it.name == gender}
            return when {
                findResult != null -> findResult
                else -> NOT_FOUND
            }
        }
    }
}