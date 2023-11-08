package com.gunpang.data.repository

import android.util.Log
import com.gunpang.data.api.Api
import com.gunpang.data.api.CalendarApi
import com.gunpang.data.model.response.CalendarRecordResDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class CalendarRecordRepository(
    private val api: CalendarApi = Api.getInstance().create(CalendarApi::class.java)

) {
    fun findRecordFromDate(date : String): Flow<CalendarRecordResDto> = flow {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formattedDate = date
            .format(formatter)

        val response = api.getCalendarRecord(formattedDate)
        if (response.code() == 200) {
            response.body()?.let {
                emit(response.body()!!)
            }
        }
    }
}