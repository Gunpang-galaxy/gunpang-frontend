package com.gunpang.data.api

import com.gunpang.common.code.MealRecordCode
import com.gunpang.data.model.request.SleepDataReqDto
import com.gunpang.data.model.request.SleepHealthConnectReqDto
import com.gunpang.data.model.response.TodayRecordResDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TodayHistoryApi {
    @GET("records/watch")
    suspend fun watchTodayRecord(@Query("date") date:String): Response<TodayRecordResDto>
    @POST("records/food")
    suspend fun watchRecordFood(@Body foodType : MealRecordCode): Response<String>

    @POST("records/sleep")
    suspend fun watchRecordSleep(@Body sleepDataReqDto : SleepDataReqDto) : Response<String>

    @POST("records/sleep/samsung")
    suspend fun watchRecordSleepFromHealthConnect(@Body sleepHealthConnectReqDto: SleepHealthConnectReqDto): Response<Unit>

    @POST("records/exercise/complete")
    suspend fun watchRecordExerciseComplete() : Response<String>
}