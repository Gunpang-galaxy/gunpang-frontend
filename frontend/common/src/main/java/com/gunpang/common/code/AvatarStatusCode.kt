package com.gunpang.common.code

enum class AvatarStatusCode(
    val status: String
) {
    ALIVE("생존"),
    DEAD("사망"),
    GRADUATED("졸업"),
    NONE("상태 정보 없음");

    companion object {
        fun fromString(status: String): AvatarStatusCode {
            var findResult : AvatarStatusCode? = values().find { it.name == status }
            return when {
                findResult != null -> findResult
                else -> NONE
            }
        }
    }
}