package com.gunpang.data.api

import com.gunpang.data.model.request.LoginReqDto
import com.gunpang.data.model.response.JwtRecreateResDto
import com.gunpang.data.model.response.LoginResDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {
    @GET("users")
    suspend fun login(@Query("googleId") googldId: String) : Response<LoginResDto>

    @POST("users/jwt/recreate")
    suspend fun recreate(@Query("JWTToken") refreshToken: String) : Response<JwtRecreateResDto>
}