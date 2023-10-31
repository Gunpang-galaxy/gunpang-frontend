package com.gunpang.data.repository

import com.gunpang.data.api.Api
import com.gunpang.data.api.AuthApi
import com.gunpang.data.model.request.LoginReqDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class AuthRepository (
){
    private val api: AuthApi = Api.getInstance().create(AuthApi::class.java)
    @Throws(IOException::class)
    fun watchLogin(loginReqDto: LoginReqDto): Flow<Boolean> = flow {
        val response = api.login(loginReqDto)
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

    fun recreate() {
        // 토큰 만료시 재요청
    }
}
