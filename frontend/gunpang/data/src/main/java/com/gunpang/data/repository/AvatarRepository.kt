package com.gunpang.data.repository

import android.util.Log
import com.gunpang.data.api.Api
import com.gunpang.data.api.AvatarApi
import com.gunpang.data.model.response.CurrentAvatarResDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AvatarRepository(
    private val api: AvatarApi = Api.getInstance().create(AvatarApi::class.java)
) {
    fun findCurrentAvatar(): Flow<CurrentAvatarResDto> = flow{
        val response = api.currentAvatar()
        if(response.code()== 200){
            response.body()?.let{
                emit(response.body()!!)
            }?: run {
                Log.d("AVATAR_REPO", "body null")
            }
        }
    }
}