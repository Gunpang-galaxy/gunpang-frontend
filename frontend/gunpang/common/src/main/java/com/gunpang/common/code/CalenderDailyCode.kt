package com.gunpang.common.code

import com.gunpang.common.R
enum class CalenderDailyCode(
    val imageId: Int,
    val contentDescription: String,
    val record: Int
) {
    ALL_DONE(imageId = R.drawable.ic_calender_daliy_all_done, contentDescription = "해당 일자 목표 모두 달성", record = 0),
    PARTIAL_DONE_ONE(imageId = R.drawable.ic_calender_daliy_partial_done, contentDescription = "해당 일자 목표 일부 달성 - 데미지 1", record = 1),
    PARTIAL_DONEPTWO(imageId = R.drawable.ic_calender_daliy_partial_done, contentDescription = "해당 일자 목표 일부 달성 - 데미지 2", record = 2),
    NOT_DONE(imageId = R.drawable.ic_calender_daliy_not_done, contentDescription = "해당 일자 목표 달성 실패", record = 3),
    NOT_RECORDED(imageId = R.drawable.ic_calender_daliy_not_record, contentDescription = "해당 일자 목표 기록 없음", record = -1);

    companion object {
        fun fromRecord(record: Int): CalenderDailyCode {
            var findResult : CalenderDailyCode? = values().find { it.record == record }
            return when {
                findResult != null -> findResult
                else -> NOT_RECORDED
            }
        }
    }
}