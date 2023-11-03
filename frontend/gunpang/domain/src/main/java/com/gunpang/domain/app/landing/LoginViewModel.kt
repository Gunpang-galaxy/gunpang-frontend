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
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.gunpang.common.code.InitCode
import com.gunpang.data.repository.AuthRepository


class LoginViewModel(
    private var mGoogleSignInClient: GoogleSignInClient,
    private val resultLauncher: ActivityResultLauncher<Intent>,
    application: Application
) : AndroidViewModel(application) {

    private val authRepository: AuthRepository = AuthRepository()

    var initCode by mutableStateOf(InitCode.NOT_FOUND)
    private var playerId by mutableStateOf("") // google

    // 건팡 로그인
    fun login() {
        Log.d("login", "login 진입")
        val signInIntent = mGoogleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
        Log.d("login", "signInIntent: $signInIntent")
    }

    class LoginViewModelFactory(
        private val mGoogleSignInClient: GoogleSignInClient,
        private val resultLauncher: ActivityResultLauncher<Intent>,
        private val application: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return LoginViewModel(mGoogleSignInClient, resultLauncher, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}