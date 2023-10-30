package com.gunpang.data.repository

import com.gunpang.data.model.request.LoginReqDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class AuthRepository (
){
    @Throws(IOException::class)
    fun watchLogin(loginReqDto: LoginReqDto): Flow<Boolean> = flow {
//        val response = api.watchLogin(loginReqDto)
//        if (response.code() == 200) {
//            response.body()?.let {
//                GunpangDataApplicationRepository().setValue("accessToken", response.body()!!.accessToken)
//                GunpangDataApplicationRepository().setValue("refreshToken", response.body()!!.refreshToken)
//                emit(true)
//            }
//        } else {
//            emit(false)
//        }
    }
}
