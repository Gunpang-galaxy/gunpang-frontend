package com.gunpang.data.api

import com.gunpang.data.model.response.CalendarRecordResDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CalendarApi {

    @GET("records/calendar")
    suspend fun getCalendarRecord(@Query(value = "date") date:String ): Response<CalendarRecordResDto>
}