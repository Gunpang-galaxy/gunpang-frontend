package com.gunpang.data.api

import com.gunpang.data.model.request.BodyCompositionApiReqDto

import kotlin.Unit;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.Response;


interface BodyCompositionApi {

        @POST("body-compositions/samsung")
        suspend fun registerBodyComposition(@Body bodyCompositionApiReqDto: BodyCompositionApiReqDto): Response<Unit>

}