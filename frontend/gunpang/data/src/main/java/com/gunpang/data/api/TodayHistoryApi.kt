package com.gunpang.data.api

import com.gunpang.common.code.MealRecordCode
import com.gunpang.data.model.request.FoodReqDto
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
    suspend fun watchRecordFood(@Query("foodType") foodType : String): Response<String>
}