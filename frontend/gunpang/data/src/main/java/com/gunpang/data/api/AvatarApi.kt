package com.gunpang.data.api

import com.gunpang.data.model.response.CurrentAvatarResDto
import retrofit2.Response
import retrofit2.http.GET

interface AvatarApi {
    @GET("/avatars/current")
    suspend fun currentAvatar(): Response<CurrentAvatarResDto>

}