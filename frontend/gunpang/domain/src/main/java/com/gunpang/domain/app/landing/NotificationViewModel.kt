package com.gunpang.domain.app.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gunpang.data.model.request.NewFCMTokenReqDto
import com.gunpang.data.repository.DataApplicationRepository
import com.gunpang.data.repository.NotificationRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class NotificationViewModel: ViewModel() {

    private val notificationRepository = NotificationRepository()

    // fcm 토큰 등록
    fun registerFCMTokens() {
        val token = DataApplicationRepository().getValue("fcmToken")
        val newFCMTokenReq = NewFCMTokenReqDto(token)
        viewModelScope.launch {
            notificationRepository.registerFCMTokens(newFCMTokenReq)
                .catch {  }
                .collect {

                }
        }
    }

}