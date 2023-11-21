package com.gunpang.common.code

enum class DayCode(
    val kor: String,
    val bitCount: Int
) {
    SUN("일", 0b01000000),
    MON("월", 0b00100000),
    TUE("화", 0b00010000),
    WED("수", 0b00001000),
    THU("목", 0b00000100),
    FRI("금", 0b00000010),
    SAT("토", 0b00000001);

    companion object {
        fun fromBitCount(week: Int): List<DayCode> {
            val result = mutableListOf<DayCode>()
            values().forEach {
                if (it.bitCount and week != 0) {
                    result.add(it)
                }
            }
            return result
        }

        fun fromKor(kor : String) : DayCode{
            return values().find { it.kor == kor }!!
        }
    }
}