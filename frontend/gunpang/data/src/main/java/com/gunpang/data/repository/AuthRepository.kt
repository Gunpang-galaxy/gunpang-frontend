package com.gunpang.data.repository

import com.gunpang.data.api.Api
import com.gunpang.data.api.AuthApi
import com.gunpang.data.model.request.LoginReqDto
import com.gunpang.data.model.request.SignUpReqDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class AuthRepository (
){
    private val api: AuthApi = Api.getInstance().create(AuthApi::class.java)

    fun appLogin(loginReqDto: LoginReqDto): Flow<Boolean> = flow {
        val response = api.appLogin(loginReqDto.googleId)
        if (response.code() == 200) {
            response.body()?.let {
                DataApplicationRepository().setValue("accessToken", response.body()!!.accessToken)
                DataApplicationRepository().setValue("refreshToken", response.body()!!.refreshToken)
                emit(true)
            }
        } else if (response.code() == 404) { // 회원가입이 되지 않은 경우
            emit(true)
        } else {
            emit(false)
        }
    }

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

    // 회원 가입
    @Throws(IOException::class)
    fun registerPersonalInfo(signUpReqDto: SignUpReqDto): Flow<Boolean> = flow {
        val response = api.register(signUpReqDto)
        if (response.code() == 200) {
            emit(true)
        } else {
            emit(false)
        }
    }

    // 개인 정보 확인
    @Throws(IOException::class)
    fun getPersonalInfo(): Flow<Boolean> = flow {
        val response = api.getPersonalInfo()
        if (response.code() == 200) {
            emit(true)
        } else {
            emit(false)
        }
    }

    fun recreate() {
        // 토큰 만료시 재요청
    }
}
