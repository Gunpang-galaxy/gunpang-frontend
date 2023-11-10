package com.gunpang.data.api

import com.gunpang.data.model.request.SleepGoalReqDto
import com.gunpang.data.model.response.MonthlyGoalResDtos
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface GoalApi {
    @POST("goals/sleep")
    suspend fun setGoal(@Body sleepGoalReqDto: SleepGoalReqDto) : Response<SleepGoalReqDto>

    @GET("goals/monthly")
    suspend fun getMonthlyGoal(
        @Query("year") year: Int,
        @Query("month") month: Int
    ): Response<MonthlyGoalResDtos>
}