package com.gunpang.data.api

import com.gunpang.data.model.request.NameAvatarReqDto
import com.gunpang.data.model.response.AppAvatarInfoResDto
import com.gunpang.data.model.response.WatchCurrentAvatarResDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AvatarApi {
    @GET("avatars/watch-current")
    suspend fun watchCurrentAvatar(): Response<WatchCurrentAvatarResDto>

    // 현재 아바타 보기 (앱)
    @GET("avatars/app-current")
    suspend fun getAvatarCurrentInfo(): Response<AppAvatarInfoResDto>

    // 아바타 정보 보기 (앱)
    @GET("avatars/{avatarId}")
    suspend fun getAvatarInfo(@Path(value = "avatarId") avatarId: Int): Response<AppAvatarInfoResDto>

    // 랜덤 아바타 등록
    @POST("avatars/gatcha")
    suspend fun registerRandomAvatar(@Body nameAvatarReqDto: NameAvatarReqDto): Response<Boolean>
}