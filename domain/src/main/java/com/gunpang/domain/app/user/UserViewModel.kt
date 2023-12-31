package com.gunpang.domain.app.user

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gunpang.common.code.GenderCode
import com.gunpang.data.repository.UserRepository
import com.gunpang.domain.entity.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/**
 * MyPage User 정보를 위한 ViewModel
 */
class UserViewModel : ViewModel()  {
    var userInfo by mutableStateOf<UserInfo>(UserInfo())
    private var userRepository: UserRepository = UserRepository()

    private fun getUserInfo(){
        viewModelScope.launch(Dispatchers.IO){
            userRepository.getUserInfo()
                .catch{
                    it.printStackTrace()
                }
                .collect{data ->
                    userInfo = UserInfo(
                        data.email,
                        GenderCode.fromString(data.gender), //genderCode 수정되면 변경
                        data.birthYear,
                        data.height
                    )
                }
        }

    }
    fun init(){
        getUserInfo()
    }

    fun logout() {
        Log.d("UserViewModel", "appLogout 전")
        viewModelScope.launch {
            userRepository.appLogout()
                .catch {  }
                .collect {

                }
        }
    }

    // 회원 탈퇴
    fun quit() {
        viewModelScope.launch {
            userRepository.userQuit()
                .catch {  }
                .collect {
                }
        }
    }

}