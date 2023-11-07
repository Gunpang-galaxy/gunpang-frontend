package com.gunpang.data.repository

import android.util.Log
import com.gunpang.data.api.Api
import com.gunpang.data.api.AvatarApi
import com.gunpang.data.model.request.NameAvatarReqDto
import com.gunpang.data.model.response.AppAvatarInfoResDto
import com.gunpang.data.model.response.WatchCurrentAvatarResDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import kotlin.jvm.Throws

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

    fun getAvatarCurrentInfo(): Flow<AppAvatarInfoResDto?> = flow{
        val response = api.getAvatarCurrentInfo()
        if(response.code() == 200){
            response.body()?.let{
                emit(response.body()!!)
            }?: run {
                Log.d("APP_CURRENT_AVATAR_REPO", "body null")
            }
        }
        else if(response.code() == 404){
            Log.d("APP_CURRENT_AVATAR_REPO", response.message())
            emit(null)
        }
    }

    fun getAvatarInfo(avatarId: Int): Flow<AppAvatarInfoResDto> = flow{
        val response = api.getAvatarInfo(avatarId)
        if(response.code() == 200){
            response.body()?.let{
                emit(response.body()!!)
            }?: run {
                Log.d("AVATAR_INFO_REPO", "body null")
            }
        }
        else if(response.code() == 404){
            Log.d("AVATAR_INFO_REPO", response.message())
        }
    }

    @Throws(IOException::class)
    fun registerRandomAvatar(nameAvatarReqDto: NameAvatarReqDto): Flow<Boolean> = flow{
        val response = api.registerRandomAvatar(nameAvatarReqDto)
        if(response.code() == 200){
            response.body()?.let{
                emit(true)
            }
        } else {
            emit(false)
        }
    }
}