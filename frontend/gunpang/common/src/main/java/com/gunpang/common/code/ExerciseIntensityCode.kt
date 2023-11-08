package com.gunpang.common.code

enum class ExerciseIntensityCode(
    val exerciseIntensity: String,
    val engUppercase: String
) {
    HIGH("고강도", "HIGH"),
    MEDIUM("중강도", "MEDIUM"),
    LOW("저강도","LOW"),
    NOT("운동이 아님", "NOT"),
    NOT_FOUND("찾을 수 없음", "NOT_FOUND");
    companion object {
        fun fromString(exerciseIntensity: String): ExerciseIntensityCode {
            var findResult : ExerciseIntensityCode ? = ExerciseIntensityCode.values().find { it.name == exerciseIntensity}
            return when {
                findResult != null -> findResult
                else -> NOT_FOUND
            }
        }
    }
}