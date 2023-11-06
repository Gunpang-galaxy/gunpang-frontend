package com.gunpang.domain.app

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppViewModel(
    application: Application
) : AndroidViewModel(application){

    //mutableStateOf 지정해서 변경되는 값들을 관리
    var findAvatarLoadingState by mutableStateOf(false) // 아바타 찾는데 로딩중인지

//    fun init() {
//        viewModelScope.launch(Dispatchers.Main) {
//            // TODO : 현재 아바타 정보 가져오기
//        }
//    }

}

class AppViewModelFactory(private val application : Application) : ViewModelProvider.Factory {
    // ViewModelProvider.Factory를 확장함.
    // 오버라이드 하면 아래와 같은 create 함수를 받을 수 있음.
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // modelClass에 MainActivityViewModel이 상속이 되었나요? 라고 물어봅니다.
        if(modelClass.isAssignableFrom(AppViewModel::class.java)){
            // 맞다면 MainViewModel의 파라미터 값을 넘겨주죠.
            return AppViewModel(application = application) as T
        }
        // 상속이 되지 않았다면 IllegalArgumentException을 통해 상속이 되지 않았다는 에러를 띄웁니다.
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}