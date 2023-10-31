package com.gunpang.domain.watch

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.wear.remote.interactions.RemoteActivityHelper
import com.google.android.gms.wearable.CapabilityClient
import com.google.android.gms.wearable.Node
import com.gunpang.common.code.InitCode
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.wear.phone.interactions.PhoneTypeHelper
import com.google.android.gms.wearable.MessageClient
import com.gunpang.data.model.request.LoginReqDto
import com.gunpang.data.repository.AuthRepository
import com.gunpang.data.repository.DataApplicationRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.guava.await
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class WatchLandingViewModel(
    private var capabilityClient: CapabilityClient,
    private var remoteActivityHelper: RemoteActivityHelper,
    private val messageClient: MessageClient,
    application: Application
)  : AndroidViewModel(application){
    companion object {
        private const val START_APP_ACTIVITY_PATH = "/start-activity"
        private const val CAPABILITY_PHONE_APP = "app_gunpang"
        private const val ANDROID_MARKET_APP_URI = "market://details?id=com.gunpang"
    }
    private val authRepository: AuthRepository = AuthRepository()
    private val dataApplicationRepository = DataApplicationRepository()
    var initCode by mutableStateOf(InitCode.NOT_FOUND)
    var androidPhoneNodeWithApp: Node? = null
    var playerId: String? = null
    /** [1] 최초 호출 **/
    fun init(){
        viewModelScope.launch {
            initPlayerId()

            if (playerId != "") {
                login()
            }else{
                checkIfAppInstalled()
            }
        }
    }
    /** [1] 휴대폰 - 앱 설치 여부 확인
    NOT_FOUND, NOT_INSTALL, NOT_LOGIN(앱설치성공) **/
    fun checkIfAppInstalled(){
        viewModelScope.launch {
            try {
                val capabilityInfo = capabilityClient
                    .getCapability(CAPABILITY_PHONE_APP, CapabilityClient.FILTER_ALL)
                    .await()

                withContext(Dispatchers.Main) {
                    androidPhoneNodeWithApp = capabilityInfo.nodes.firstOrNull()
                    // 모바일 앱 설치 안됨
                    initCode = if (androidPhoneNodeWithApp == null) {
                        InitCode.NOT_INSTALL
                    } else {
                        InitCode.NOT_LOGIN
                    }
                }
            } catch (_: Exception) {}
        }
    }
    /** [2] 로그인 정보 O -> 로그인(NOT_CONFIG) 로그인 실패(NOT_LOGIN) **/
    fun login(){
        viewModelScope.launch {
            initPlayerId()
            authRepository.watchLogin(LoginReqDto(playerId!!))
                .catch {
                    initCode = InitCode.NOT_LOGIN
                }
                .collect { data ->
                    initCode = if (data) {
                        InitCode.NOT_CONFIG
                    } else InitCode.NOT_LOGIN
                }
        }
    }
    /** [3] 아바타 Config 진행하기 **/
    fun config(){
        viewModelScope.launch {
            //TODO: avatar가 있는 지 확인
        }
    }
    private fun initPlayerId(){
        if (playerId == null) {
            playerId = dataApplicationRepository.getValue("playerId")
        }
    }

    /** 모바일 앱 설치 위해 Google Play Store 원격으로 열기 **/
    fun openAppInStoreOnPhone() {
        val intent = when (PhoneTypeHelper.getPhoneDeviceType(getApplication())) {
            PhoneTypeHelper.DEVICE_TYPE_ANDROID -> {
                Intent(Intent.ACTION_VIEW)
                    .addCategory(Intent.CATEGORY_BROWSABLE)
                    .setData(Uri.parse(ANDROID_MARKET_APP_URI))
            }
            else -> {
                return
            }
        }

        viewModelScope.launch {
            try {
                remoteActivityHelper.startRemoteActivity(intent).await()
            } catch (cancellationException: CancellationException) {
                throw cancellationException
            } catch (_: Exception) {}
        }
    }

    // 초기 설정을 위해 원격으로 모바일 앱 실행하는 함수
    fun openAppOnPhone() {
        viewModelScope.launch {
            try {
                val nodes = capabilityClient
                    .getCapability(CAPABILITY_PHONE_APP, CapabilityClient.FILTER_REACHABLE)
                    .await()
                    .nodes
                nodes.map { node ->
                    async {
                        messageClient.sendMessage(node.id,
                            START_APP_ACTIVITY_PATH, byteArrayOf())
                            .await()
                    }
                }.awaitAll()

            } catch (_: Exception) {}
        }
    }

}

class WatchLandingViewModelFactory(
    private var capabilityClient: CapabilityClient,
    private val remoteActivityHelper: RemoteActivityHelper,
    private val messageClient:MessageClient,
    private val application: Application
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WatchLandingViewModel::class.java)){
            return WatchLandingViewModel(capabilityClient, remoteActivityHelper,messageClient, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}