package com.gunpang.domain.app.landing

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gunpang.data.model.request.SignUpReqDto
import com.gunpang.data.repository.DataApplicationRepository
import com.gunpang.data.repository.UserRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class PersonalInfoViewModel: ViewModel() {

    private val userRepository = UserRepository()

    // 등록된 개인 정보가 있다면 메인화면으로 이동
    // 순차 실행을 위해 runBlocking 사용
    fun hasPersonalInfo(): Boolean {
        if (DataApplicationRepository().getValue("accessToken").isEmpty()) return false // 토큰이 없는 경우
        Log.d("personalInfo", "accessToken: ${DataApplicationRepository().getValue("accessToken")}")

        var result = false
        runBlocking {
            userRepository.getUserInfo()
                .catch {}
                .collect {// 개인 정보가 등록된 경우
                    result = true
                }
        }
        return result
    }

    // 회원가입 + 개인 정보 등록
    fun registerPersonalInfo(age: String, height: String, gender: String) {
        viewModelScope.launch {
            val googleId = DataApplicationRepository().getValue("playerId")
            val email = DataApplicationRepository().getValue("email")
            val signUpReqDto = SignUpReqDto(gender = gender, birthYear = age.toInt(), height = height.toInt(), googleId = googleId, email = email)
            userRepository.registerPersonalInfo(signUpReqDto) // 회원가입
                .catch {}
                .collect { data ->
                    if (data) { // 회원가입 성공
                        Log.d("personalInfo", "회원가입 성공")
                    } else { // 회원가입 실패
                        Log.d("personalInfo", "회원가입 실패")
                    }
                }
        }
    }

}