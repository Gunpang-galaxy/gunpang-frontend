package com.gunpang.data.repository

import android.util.Log
import com.gunpang.data.api.Api
import com.gunpang.data.api.UserApi
import com.gunpang.data.model.request.LoginReqDto
import com.gunpang.data.model.request.SignUpReqDto
import com.gunpang.data.model.response.UserInfoResDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class UserRepository(

    private val api: UserApi = Api.getInstance().create(UserApi::class.java)
) {

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

    // 회원 가입
    @Throws(IOException::class)
    fun registerPersonalInfo(signUpReqDto: SignUpReqDto): Flow<Boolean> = flow {
        val response = api.register(signUpReqDto)
        if (response.code() == 200) {
            DataApplicationRepository().setValue("accessToken", response.body()!!.accessToken)
            DataApplicationRepository().setValue("refreshToken", response.body()!!.refreshToken)
            emit(true)
        } else {
            emit(false)
        }
    }

    // 개인 정보 확인
    @Throws(IOException::class)
    fun getUserInfo() : Flow<UserInfoResDto> = flow {
        val response = api.userInfo()
        if(response.code() == 200){
            response.body()?.let{
                emit(response.body()!!)
            }
        }
    }

    // 로그아웃
    @kotlin.jvm.Throws(IOException::class)
    fun appLogout(): Flow<Boolean> = flow {
        Log.d("UserRepository", "appLogout 시작")
        val response = api.appLogout()
        Log.d("UserRepository", response.toString())
        if (response.code() == 200) {
            Log.d("UserRepository", "response.code() == 200")
            Log.d("UserRepository", "토큰 제거 전")
            DataApplicationRepository().removeValue("accessToken")
            DataApplicationRepository().removeValue("refreshToken")
            Log.d("UserRepository", "토큰 제거 후")
            emit(true)
        } else {
            Log.d("UserRepository", "response.code() != 200")
        }
        Log.d("UserRepository", "appLogout 끝")
        emit(false)
    }

    // 회원 탈퇴
    @Throws(IOException::class)
    fun userQuit(): Flow<Boolean> = flow {
        val response = api.appSignOut()
        if (response.code() == 200) {
            DataApplicationRepository().removeValue("playerId")
            DataApplicationRepository().removeValue("accessToken")
            DataApplicationRepository().removeValue("refreshToken")
            emit(true)
        } else {
            emit(false)
        }
    }
}