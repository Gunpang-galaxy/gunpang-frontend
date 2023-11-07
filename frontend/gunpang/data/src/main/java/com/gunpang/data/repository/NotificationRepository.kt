package com.gunpang.data.repository

import com.gunpang.data.api.Api
import com.gunpang.data.api.NotificationApi
import com.gunpang.data.model.request.NewFCMTokenReqDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import kotlin.jvm.Throws

class NotificationRepository {
    private val api: NotificationApi = Api.getInstance().create(NotificationApi::class.java)

    @Throws(IOException::class)
    fun registerFCMTokens(newFCMTokenReqDto: NewFCMTokenReqDto): Flow<Boolean> = flow {
        val response = api.registerFCMTokens(newFCMTokenReqDto)
        if (response.code() == 200){
            emit(true)
        } else {
            emit(false)
        }
    }

}