package com.gunpang.data.api

import com.gunpang.data.model.request.LoginReqDto
import com.gunpang.data.model.response.LoginResDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body loginReqDto: LoginReqDto) : Response<LoginResDto>

}