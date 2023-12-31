/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.gunpang.app

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.wear.remote.interactions.RemoteActivityHelper


import com.google.android.gms.wearable.CapabilityClient
import com.google.android.gms.wearable.CapabilityInfo
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.Wearable
import com.gunpang.data.repository.DataApplicationRepository
import com.gunpang.domain.watch.WatchLandingViewModel
import com.gunpang.domain.watch.WatchLandingViewModelFactory
import com.gunpang.ui.WatchMain
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), CapabilityClient.OnCapabilityChangedListener  {
    companion object {
        private const val PERMISSION_CHECK = 100
    }
    // 모바일 - 워치
    private lateinit var capabilityClient: CapabilityClient
    private lateinit var remoteActivityHelper: RemoteActivityHelper
    private lateinit var messageClient: MessageClient

    // LandingViewModel
    private lateinit var watchLandingViewModelFactory : WatchLandingViewModelFactory
    private lateinit var watchLandingViewModel : WatchLandingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("테스트","onCreate")
        // 필수 권한 부여
        grantPermissions()
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BODY_SENSORS)
            != PackageManager.PERMISSION_GRANTED) {
            Log.d("테스트", "권한 설정 안되어있음")
        }

        // 모바일 - 워치 연결
        capabilityClient = Wearable.getCapabilityClient(this)
        remoteActivityHelper = RemoteActivityHelper(this)
        messageClient = Wearable.getMessageClient(this)

        // Landing 과정에 필요한 ViewModel 생성
        watchLandingViewModelFactory =
            WatchLandingViewModelFactory(
                capabilityClient,
                remoteActivityHelper,
                messageClient,
                this.application
            )
        watchLandingViewModel = ViewModelProvider(this@MainActivity, watchLandingViewModelFactory)[WatchLandingViewModel::class.java]

        // 화면 켜짐 유지
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContent {
            WatchMain(watchLandingViewModel)
        }
    }

    private fun grantPermissions() {
        //TODO("필요한 권한 확인후, list에 넣을 것")
        val needPermissions = listOf(
            Manifest.permission.BODY_SENSORS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACTIVITY_RECOGNITION).toTypedArray()
        ActivityCompat.requestPermissions(this, needPermissions,PERMISSION_CHECK);
    }

    /** 연결된 기기의 정보가 변경되었을 때 **/
    override fun onCapabilityChanged(capabilityInfo: CapabilityInfo) {
        if (DataApplicationRepository().getValue("playerId") == "") {
            watchLandingViewModel.checkIfAppInstalled()
        }
    }
}
