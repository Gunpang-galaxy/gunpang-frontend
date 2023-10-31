package com.gunpang.data.api

import com.gunpang.data.model.response.WatchCurrentAvatarResDto
import retrofit2.Response
import retrofit2.http.GET

interface AvatarApi {
    @GET("/avatars/watch-current")
    suspend fun watchCurrentAvatar(): Response<WatchCurrentAvatarResDto>


}