package com.gunpang.data.repository

import android.util.Log
import com.gunpang.data.api.Api
import com.gunpang.data.api.AvatarApi
import com.gunpang.data.model.response.WatchCurrentAvatarResDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AvatarRepository(
    private val api: AvatarApi = Api.getInstance().create(AvatarApi::class.java)
) {
    fun findWatchCurrentAvatar(): Flow<WatchCurrentAvatarResDto> = flow{
        val response = api.watchCurrentAvatar()
        if(response.code() == 200){
            response.body()?.let{
                emit(response.body()!!)
            }?: run {
                Log.d("AVATAR_REPO", "body null")
            }
        }
    }

}