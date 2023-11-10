package com.gunpang.domain.app.landing

import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.wear.remote.interactions.RemoteActivityHelper
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.wearable.CapabilityClient
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.Node
import com.google.android.gms.wearable.NodeClient
import com.gunpang.common.code.InitCode
import com.gunpang.data.model.request.LoginReqDto
import com.gunpang.data.repository.DataApplicationRepository
import com.gunpang.data.repository.UserRepository
import com.gunpang.domain.app.user.UserViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class LandingViewModel(
    private val mGoogleSignInClient: GoogleSignInClient,
    private val signInIntent: Intent,
    private val resultLauncher: ActivityResultLauncher<Intent>,
    private val capabilityClient: CapabilityClient,
    private val nodeClient: NodeClient,
    private val remoteActivityHelper: RemoteActivityHelper,
    private var messageClient: MessageClient,
    private val bluetoothIntent: Intent,
    private val appInstallIntent: Intent,
    private val application: Application
) : AndroidViewModel(application) {
    companion object {
        private const val START_WEAR_ACTIVITY_PATH = "/start-activity"
        private const val CAPABILITY_WEAR_APP = "watch_gunpang"
    }

    var initCode by mutableStateOf(InitCode.NOT_CONFIG)

    private val userRepository: UserRepository = UserRepository()
    private val userViewModel: UserViewModel = UserViewModel()

    var wearNodesWithApp: Set<Node>? = null // 핸드폰과 연결된 wearable 기기 중 워치에 건팡 앱이 설치된 기기의 수
    var allConnectedNodes: List<Node>? = null // 핸드폰과 연결된 wearable 기기의 수
    private var playerId by mutableStateOf("")

    // [회원 관리 관련 코드 START]
    // 건팡 로그인
    fun login() {
        // 워치에 앱이 설치된 경우에만 실행
        if (findWearableConnectedStatus()) {
            resultLauncher.launch(signInIntent) // 로그인 상태 확인
        }
    }

    // 로그인 요청
    fun doLoginRequest() {
        viewModelScope.launch {
            val loginReq = LoginReqDto(googleId = DataApplicationRepository().getValue("playerId"))
            userRepository.appLogin(loginReq)
                .catch {
                    initCode = InitCode.NOT_LOGIN // 로그인 실패 시 로그인 페이지로 재이동
                }
                .collect { data ->
                    initCode = if (data && DataApplicationRepository().getValue("accessToken").isNotEmpty()
                    ) { // 로그인 성공
                        InitCode.FINISH
                    } else if (data && DataApplicationRepository().getValue("accessToken").isEmpty()
                    ) { // 회원가입 필요
                        InitCode.REGISTER
                    } else { // 로그인 실패
                        InitCode.NOT_LOGIN
                    }
                }
        }
    }

    // 로그아웃
    fun logout() {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    Log.d("UserViewModel", "로그아웃 성공")
                }
            }
        userViewModel.logout()
    }

    // 회원 탈퇴
    fun quit() {
        mGoogleSignInClient.revokeAccess()
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    Log.d("UserViewModel", "회원 탈퇴 성공")
                }
            }
        userViewModel.quit()
    }
    // [회원 관리 관련 코드 END]

    // [웨어러블 코드 START]
    // 웨어러블 연결 설정으로 이동
    fun requestWearableConnection() {
        resultLauncher.launch(bluetoothIntent)
    }

    // 웨어러블 연결 상태 확인
    fun requestWearableAppInstall() {
        val wearNodesWithApp = wearNodesWithApp ?: return
        val allConnectedNodes = allConnectedNodes ?: return
        val nodesWithoutApp = allConnectedNodes - wearNodesWithApp

        nodesWithoutApp.forEach { node ->
            Log.d("wearOS", "nodesWithoutApp: displayName ${node.displayName} / id ${node.id} / nearby ${node.isNearby}")
            remoteActivityHelper
                .startRemoteActivity(
                    targetIntent = appInstallIntent,
                    targetNodeId = node.id
                )
        }
    }

    // 웨어러블 등록 여부 (근처에 있는지)
    fun findWearDevicesWithApp() {
        Log.d("wearOS", "findWearDevicesWithApp 진입")
        val pendingResult = capabilityClient.getCapability(CAPABILITY_WEAR_APP, CapabilityClient.FILTER_REACHABLE)

        pendingResult.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val capabilityInfo = task.result
                wearNodesWithApp = capabilityInfo.nodes
                Log.d("wearOS", "wearNodesWithApp: ${wearNodesWithApp!!.size}")
                wearNodesWithApp!!.forEach(
                    {
                        Log.d("wearOS", "wearNodesWithApp: displayName ${it.displayName} / id ${it.id} / nearby ${it.isNearby}")
                    }
                )
            } else {
                Log.d("wearOS", "Failed CapabilityApi: " + task.result)
            }
        }
    }

    // 웨어러블 기기 찾기
    fun findAllWearDevices() {
        val pendingResult = nodeClient.connectedNodes

        pendingResult.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                allConnectedNodes = task.result
                allConnectedNodes!!.forEach(
                    {
                        Log.d("wearOS", "allConnectedNodes: displayName ${it.displayName} / id ${it.id} / nearby ${it.isNearby}")
                    }
                )
                findWearableConnectedStatus()
            } else {
                // Task failed with an exception
                Log.e("wearOS", "Failed CapabilityApi: " + task.exception)
            }
        }
    }

    // 웨어러블 연결 상태 확인(연결된 워치 X / 연결된 기기에 앱 설치 X)
    private fun findWearableConnectedStatus(): Boolean {
        if (allConnectedNodes.isNullOrEmpty()) {
            initCode = InitCode.NOT_FOUND
            return false
        }
        else if (wearNodesWithApp.isNullOrEmpty()) {
            initCode = InitCode.NOT_INSTALL
            return false
        }
        return true
    }

    // 초기 설정을 위해 원격으로 웨어러블 앱 실행하는 함수
    fun registerWearable() {
        viewModelScope.launch {
            try {
                val nodes = capabilityClient
                    .getCapability(CAPABILITY_WEAR_APP, CapabilityClient.FILTER_REACHABLE)
                    .await()
                    .nodes
                nodes.map { node ->
                    async {
                        Log.d("테스트", "앱 playerId : $playerId")
                        Log.d("테스트", "앱 repo : ${DataApplicationRepository().getValue("playerId")}")
                        Log.d("테스트", "앱 at : ${DataApplicationRepository().getValue("accessToken")}")
                        Log.d("테스트", "앱 rt : ${DataApplicationRepository().getValue("refreshToken")}")
                        if (node.id != "") {
                            DataApplicationRepository().setValue("watchId", node.id)
                            messageClient.sendMessage(
                                node.id,
                                START_WEAR_ACTIVITY_PATH,
                                DataApplicationRepository().getValue("playerId").toByteArray()
                            )
                                .await()
                        }
                    }
                }.awaitAll()
                initCode = InitCode.FINISH
            } catch (_: Exception) {}
        }
    }
    // [웨어러블 코드 END]

}

class LandingViewModelFactory(
    private val mGoogleSignInClient: GoogleSignInClient,
    private val signInIntent: Intent,
    private val resultLauncher: ActivityResultLauncher<Intent>,
    private val capabilityClient: CapabilityClient,
    private val nodeClient: NodeClient,
    private val remoteActivityHelper: RemoteActivityHelper,
    private val messageClient: MessageClient,
    private val bluetoothIntent: Intent,
    private val appInstallIntent: Intent,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LandingViewModel::class.java)) {
            return LandingViewModel(
                mGoogleSignInClient, signInIntent, resultLauncher,
                capabilityClient, nodeClient,
                remoteActivityHelper, messageClient, bluetoothIntent, appInstallIntent,
                application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}