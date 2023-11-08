package com.gunpang.data.api

import com.gunpang.data.model.request.SleepGoalReqDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface GoalApi {
    @POST("goals/sleep")
    suspend fun setGoal(@Body sleepGoalReqDto: SleepGoalReqDto) : Response<SleepGoalReqDto>
}