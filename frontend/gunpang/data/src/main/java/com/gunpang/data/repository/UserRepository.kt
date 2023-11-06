package com.gunpang.data.repository

import com.gunpang.data.api.Api
import com.gunpang.data.api.UserApi
import com.gunpang.data.model.response.UserInfoResDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepository(
    private val api: UserApi = Api.getInstance().create(UserApi::class.java)
) {
    fun getUserInfo() : Flow<UserInfoResDto> = flow {
        val response = api.userInfo()
        if(response.code() == 200){
            response.body()?.let{
                emit(response.body()!!)
            }
        }
    }
}