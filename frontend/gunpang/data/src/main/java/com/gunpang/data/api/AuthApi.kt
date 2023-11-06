package com.gunpang.data.api

import com.gunpang.data.model.request.LoginReqDto
import com.gunpang.data.model.request.SignUpReqDto
import com.gunpang.data.model.response.LoginResDto
import com.gunpang.data.model.response.PersonalInfoResDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {
    @POST("/auth/login")
    suspend fun login(@Body loginReqDto: LoginReqDto) : Response<LoginResDto>

    @GET("/users")
    suspend fun appLogin(@Query("googleId") googleId: String) : Response<LoginResDto>

    @POST("/users")
    suspend fun register(@Body signUpReqDto: SignUpReqDto) : Response<Boolean>

    @GET("/users/info")
    suspend fun getPersonalInfo() : Response<PersonalInfoResDto>
}