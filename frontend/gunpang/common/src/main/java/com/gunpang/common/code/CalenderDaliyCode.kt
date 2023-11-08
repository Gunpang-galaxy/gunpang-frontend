package com.gunpang.common.code

import com.gunpang.common.R
enum class CalenderDaliyCode(
    val imageId: Int,
    val contentDescription: String,
    val record: Int
) {
    ALL_DONE(imageId = R.drawable.ic_calender_daliy_all_done, contentDescription = "해당 일자 목표 모두 달성", record = 0),
    PARTIAL_DONE(imageId = R.drawable.ic_calender_daliy_partial_done, contentDescription = "해당 일자 목표 일부 달성", record = 1),
    NOT_DONE(imageId = R.drawable.ic_calender_daliy_not_done, contentDescription = "해당 일자 목표 달성 실패", record = 2),
    NOT_RECORDED(imageId = R.drawable.ic_calender_daliy_not_record, contentDescription = "해당 일자 목표 기록 없음", record = -1);

    companion object {
        fun fromRecord(record: Int): CalenderDaliyCode {
            var findResult : CalenderDaliyCode? = values().find { it.record == record }
            return when {
                findResult != null -> findResult
                else -> NOT_RECORDED
            }
        }
    }
}