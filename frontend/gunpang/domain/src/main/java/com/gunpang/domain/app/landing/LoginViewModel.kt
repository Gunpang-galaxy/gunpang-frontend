package com.gunpang.domain.app.landing

import android.app.Application
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.gunpang.common.code.InitCode
import com.gunpang.data.model.request.LoginReqDto
import com.gunpang.data.repository.DataApplicationRepository
import com.gunpang.data.repository.UserRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LoginViewModel(
    private val signInIntent: Intent,
    private val resultLauncher: ActivityResultLauncher<Intent>,
    application: Application
) : AndroidViewModel(application) {

    var initCode by mutableStateOf(InitCode.NOT_FOUND)

    private val userRepository: UserRepository = UserRepository()

    // 건팡 로그인
    fun login() {
        resultLauncher.launch(signInIntent)
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
                        InitCode.NOT_CONFIG
                    } else if (data && DataApplicationRepository().getValue("accessToken").isEmpty()
                    ) { // 회원가입 필요
                        InitCode.REGISTER
                    } else { // 로그인 실패
                        InitCode.NOT_LOGIN
                    }
                }
        }
    }

    class LoginViewModelFactory(
        private val signInIntent: Intent,
        private val resultLauncher: ActivityResultLauncher<Intent>,
        private val application: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return LoginViewModel(signInIntent, resultLauncher, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}