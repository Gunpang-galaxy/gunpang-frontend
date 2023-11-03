package com.gunpang.domain.app.user

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gunpang.data.repository.UserRepository
import com.gunpang.domain.entity.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/**
 * MyPage User 정보를 위한 ViewModel
 */
class UserViewModel : ViewModel()  {
    private var userInfo = UserInfo()
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
                        data.gender, //genderCode 수정되면 변경
                        data.birth,
                        data.height
                    )
                }
        }

    }
    fun init(){

    }
}