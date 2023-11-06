package com.gunpang.domain.app.landing

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gunpang.data.model.request.LoginReqDto
import com.gunpang.data.model.request.SignUpReqDto
import com.gunpang.data.repository.AuthRepository
import com.gunpang.data.repository.DataApplicationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class PersonalInfoViewModel: ViewModel() {

    private val authRepository = AuthRepository()

    // 등록된 개인 정보가 있다면 메인화면으로 이동
    fun hasPersonalInfo(): Boolean {
        if (DataApplicationRepository().getValue("accessToken").isEmpty()) return false // 토큰이 없는 경우
        Log.d("personalInfo", "accessToken: ${DataApplicationRepository().getValue("accessToken")}")

        var result = false
        viewModelScope.launch {
            authRepository.getPersonalInfo()
                .catch {}
                .collect { data ->
                    if (data) { // 개인 정보가 등록된 경우
                        result = true
                    }
                }
        }

        return result
    }

    // 회원가입 + 개인 정보 등록
    fun registerPersonalInfo(age: String, height: String, gender: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val googleId = DataApplicationRepository().getValue("googleId")
            val signUpReqDto = SignUpReqDto(gender = gender, birthYear = age.toInt(), height = height.toInt(), googleId = googleId)
            authRepository.registerPersonalInfo(signUpReqDto) // 회원가입
                .collect { data ->
                    if (data) {
                        authRepository.appLogin(LoginReqDto(googleId = googleId)) // 회원가입 했다면 token 발급
                    }
                }
        }
    }

}