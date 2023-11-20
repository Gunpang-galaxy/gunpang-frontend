package com.gunpang.data.api

import com.gunpang.data.model.request.BodyCompositionApiReqDto
import com.gunpang.data.model.response.BodyCompositionInfoResDto
import retrofit2.Response
import retrofit2.http.GET
import kotlin.Unit;
import retrofit2.http.Body;
import retrofit2.http.POST;

interface BodyCompositionApi {

    @GET("body-compositions/")
    suspend fun bodyCompositionInfo() : Response<BodyCompositionInfoResDto>

    @POST("body-compositions/samsung")
    suspend fun registerBodyComposition(@Body bodyCompositionApiReqDto: BodyCompositionApiReqDto): Response<Unit>

}

