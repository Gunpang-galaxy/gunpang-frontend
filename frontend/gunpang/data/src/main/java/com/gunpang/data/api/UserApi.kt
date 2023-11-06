package com.gunpang.data.api

import com.gunpang.data.model.response.UserInfoResDto
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {
    @GET("/users/info")
    suspend fun userInfo() : Response<UserInfoResDto>
}