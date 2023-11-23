package com.gunpang.data.repository

import android.util.Log
import com.gunpang.data.api.Api
import com.gunpang.data.api.AuthApi
import com.gunpang.data.model.request.LoginReqDto
import com.gunpang.data.model.response.JwtRecreateResDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class AuthRepository (
){
    private val api: AuthApi = Api.getInstance().create(AuthApi::class.java)

    @Throws(IOException::class)
    fun watchLogin(playerId: String): Flow<Boolean> = flow {
        Log.d("테스트", playerId)
        val response = api.login(playerId)
        if (response.code() == 200) {
            response.body()?.let {
                DataApplicationRepository().setValue("accessToken", response.body()!!.accessToken)
                DataApplicationRepository().setValue("refreshToken", response.body()!!.refreshToken)
                emit(true)
            }
        } else {
            emit(false)
        }
    }

    // 토큰 만료시 재요청
    @Throws(IOException::class)
    fun recreate(): Flow<JwtRecreateResDto> = flow {
        val refreshToken = DataApplicationRepository().getValue("refreshToken")
        val response = api.recreate(refreshToken)
        if (response.code() == 200) {
            response.body()?.let {
                DataApplicationRepository().setValue("accessToken", response.body()!!.accessToken)
                emit(response.body()!!)
            }
        }
    }
}
