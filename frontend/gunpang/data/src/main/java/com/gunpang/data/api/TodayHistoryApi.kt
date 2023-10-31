package com.gunpang.data.api

import com.gunpang.data.model.response.TodayHistoryResDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TodayHistoryApi {
    @GET("/records")
    suspend fun todayRecord(@Query("date") date:String): Response<TodayHistoryResDto>
}