package com.gunpang.app

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
import com.gunpang.domain.app.landing.LoginViewModel
import com.gunpang.ui.app.AppMain
import com.gunpang.ui.app.screen.notification.NotificationDialogFragment


class MainActivity : ComponentActivity() {

    // 로그인 시 사용하는 google data
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    // 로그인 view model
    private lateinit var loginViewModelFactory : LoginViewModel.LoginViewModelFactory
    private lateinit var loginViewModel : LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v("Android","SDK_INT : " +Build.VERSION.SDK_INT)

        // 알림 설정
        askNotificationPermission() // 알림 권한 확인, 없다며 요청
        checkFirebaseToken() // firebase 토큰 확인하기

        // google login 요청 options
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("1001425907106-8g8c597uvcrdk1p5pso28jdhkl5qpemu.apps.googleusercontent.com")
            .requestEmail()
            .build()

        // 로그인 시 사용하는 google data
        mGoogleSignInClient = applicationContext?.let { GoogleSignIn.getClient(it, gso) }!!
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        // 로그인 view model
        loginViewModelFactory = LoginViewModel.LoginViewModelFactory(mGoogleSignInClient, resultLauncher, this.application)
        loginViewModel = ViewModelProvider(this@MainActivity, loginViewModelFactory)[LoginViewModel::class.java]

        setContent {
            AppMain(loginViewModel = loginViewModel)
        }

    }

    // firebase token 자동 초기화 방지
    fun runtimeEnableAutoInit() {
        Firebase.messaging.isAutoInitEnabled = true
    }

    // notification 권한 화면 전환
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.
        } else {
            val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                .putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
            startActivity(intent)
        }
    }

    // Post Notifications 권한 확인 및 요청
    private fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // FCM SDK (and your app) can post notifications.
                Log.d("알림 권한", "설정O")
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                NotificationDialogFragment()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        } else {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    // firebase 토큰 확인
    private fun checkFirebaseToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(ContentValues.TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log
            val msg = "This is my FCM token: ${token}"
            Log.d(ContentValues.TAG, msg)
        })
    }

    // 로그인 시 사용하는 google data 수집
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        Log.d("loginViewModel", "result: ${result.resultCode}")
        Log.d("loginViewModel", "result: ${result.data.toString()}")
        if (result.resultCode == 1) {
            val data: Intent? = result.data
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            Log.d("loginViewModel", "task: $task")
            handleSignInResult(task)
        }
    }

    // 로그인 시 사용하는 google data 수집
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>){
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val email = account?.email.toString()
            Log.d("loginViewModel", "email: $email")
        } catch (e: ApiException){
            Log.w("failed", "signInResult:failed code=" + e.statusCode)
        }
    }

}
