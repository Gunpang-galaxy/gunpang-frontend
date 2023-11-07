package com.gunpang.data.api

import com.gunpang.data.model.request.NewFCMTokenReqDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NotificationApi {
    @POST("fcm/notification/record")
    suspend fun registerFCMTokens(@Body newFCMTokenReqDto: NewFCMTokenReqDto): Response<Unit>
}