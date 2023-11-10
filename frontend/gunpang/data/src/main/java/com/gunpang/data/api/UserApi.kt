package com.gunpang.data.api

import com.gunpang.data.model.request.SignUpReqDto
import com.gunpang.data.model.response.LoginResDto
import com.gunpang.data.model.response.SignUpResDto
import com.gunpang.data.model.response.UserInfoResDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface UserApi {
    @GET("users")
    suspend fun appLogin(@Query("googleId") googleId: String) : Response<LoginResDto>

    @POST("users")
    suspend fun register(@Body signUpReqDto: SignUpReqDto) : Response<SignUpResDto>

    @GET("users/info")
    suspend fun userInfo() : Response<UserInfoResDto>

    @GET("users/logout")
    suspend fun appLogout() : Response<Void>

    @PUT("users/signout")
    suspend fun appSignOut() : Response<Void>
}