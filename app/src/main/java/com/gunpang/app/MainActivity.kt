package com.gunpang.app

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.wear.remote.interactions.RemoteActivityHelper
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.gms.wearable.CapabilityClient
import com.google.android.gms.wearable.CapabilityInfo
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.NodeClient
import com.google.android.gms.wearable.Wearable
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
import com.gunpang.data.repository.DataApplicationRepository
import com.gunpang.domain.app.landing.LandingViewModel
import com.gunpang.domain.app.landing.LandingViewModelFactory
import com.gunpang.ui.app.AppMain
import com.gunpang.ui.app.screen.notification.NotificationDialogFragment


class MainActivity : ComponentActivity(), CapabilityClient.OnCapabilityChangedListener {
    companion object {
        private const val CAPABILITY_WEAR_APP = "watch_gunpang"
        private const val PLAY_STORE_APP_URI = "market://details?id=com.gunpang"

    }

    // 모바일-워치 간 연결
    private lateinit var capabilityClient: CapabilityClient
    private lateinit var nodeClient: NodeClient
    private lateinit var remoteActivityHelper: RemoteActivityHelper
    private lateinit var messageClient: MessageClient
    private lateinit var bluetoothIntent: Intent
    private lateinit var appInstallIntent: Intent

    // 구글 로그인
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var signInIntent: Intent

    // 로그인 view model
    private lateinit var landingViewModelFactory: LandingViewModelFactory
    private lateinit var landingViewModel: LandingViewModel

    // 헬스 커넥트 앱 ViewModel -> Factory로 관리
//    private lateinit var appHealthViewModelFactory: AppHealthViewModelFactory
//    private lateinit var appHealthViewModel: AppHealthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v("Android", "SDK_INT : " + Build.VERSION.SDK_INT)

        // 알림 설정
        askNotificationPermission() // 알림 권한 확인, 없다며 요청
        checkFirebaseToken() // firebase 토큰 확인하기

        // 모바일-워치 간 연결
        capabilityClient = Wearable.getCapabilityClient(this)
        nodeClient = Wearable.getNodeClient(this)
        remoteActivityHelper = RemoteActivityHelper(this)
        messageClient = Wearable.getMessageClient(this)
        bluetoothIntent = Intent(Settings.ACTION_BLUETOOTH_SETTINGS)
        appInstallIntent = Intent(Intent.ACTION_VIEW)
            .addCategory(Intent.CATEGORY_BROWSABLE)
            .setData(Uri.parse(PLAY_STORE_APP_URI))

        // 구글 로그인
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = applicationContext?.let { GoogleSignIn.getClient(it, gso) }!!
        signInIntent = mGoogleSignInClient.signInIntent

        // 로그인 view model
        landingViewModelFactory = LandingViewModelFactory(
            mGoogleSignInClient, signInIntent, resultLauncher,
            capabilityClient, nodeClient,
            remoteActivityHelper, messageClient, bluetoothIntent, appInstallIntent,
            this.application
        )
        landingViewModel =
            ViewModelProvider(this@MainActivity, landingViewModelFactory)[LandingViewModel::class.java]



        // 헬스 커넥트 API
        val healthConnectManager = (application as AppDataApplication).healthConnectManager

        // 헬스 ViewModel (전역으로 관리하는지 확인 필요)
//        appHealthViewModelFactory = AppHealthViewModelFactory(healthConnectManager, this.application)
//        appHealthViewModel =
//            ViewModelProvider(this@MainActivity, appHealthViewModelFactory)[AppHealthViewModel::class.java]

        setContent {
            AppMain(
                landingViewModel = landingViewModel,
                healthConnectManager = healthConnectManager,
                )
        }

    }

    // [알림 관련 코드 START]
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

            // save token
            DataApplicationRepository().setValue("fcmToken", token)
        })
    }
    // [알림 관련 코드 END]

    // [로그인 관련 코드 START]
    // 구글 로그인 실행
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        Log.d("login", "result: ${result.resultCode}")
        if (result.resultCode == -1) { // 로그인 성공
            val data: Intent? = result.data
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            task.addOnSuccessListener {
                handleSignInResult(task)
                landingViewModel.doLoginRequest()
                Log.d("landing", "initCode1: ${landingViewModel.initCode}")
            }
        }
    }

    // 로그인 시 사용하는 google data 수집
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        Log.d("login", "handleSignInResult 진입")
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val id = account?.id.toString()
            val email = account?.email.toString()
            Log.d("login", "id: $id / email: $email")
            DataApplicationRepository().setValue("playerId", id)
            DataApplicationRepository().setValue("email", email)
        } catch (e: ApiException) {
            Log.w("failed", "signInResult:failed code=" + e.statusCode)
        }
    }
    // [로그인 관련 코드 END]

    // [헬스 커넥트 관련 코드 START]
//    fun AppHealthGetPermissions(){
//        val needPermissions = listOf{
//            Manifest.permission.
//        }
//    }
    // [헬스 커넥트 관련 코드 END]

    // [웨어러블 관련 코드 START]
    private fun connected() {
        capabilityClient.addListener(this, Uri.parse("wear://"), CapabilityClient.FILTER_REACHABLE)
        capabilityClient.addListener(this, CAPABILITY_WEAR_APP)

        // 워치 연결 확인(앱 설치 여부)
        landingViewModel.findWearDevicesWithApp()
        landingViewModel.findAllWearDevices()
    }

    // 웨어러블 연결 여부 확인 후
    override fun onCapabilityChanged(capabilityInfo: CapabilityInfo) {
        landingViewModel.wearNodesWithApp = capabilityInfo.nodes
        landingViewModel.findAllWearDevices()
    }
    // [웨어러블 관련 코드 END]

    // 앱 중지 마다 리스너 해제
    override fun onPause() {
        super.onPause()
        capabilityClient.removeListener(this, CAPABILITY_WEAR_APP)
    }

    override fun onResume() {
        super.onResume()
        connected()
    }
}
