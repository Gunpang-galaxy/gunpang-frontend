/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.gunpang.watch.presentation

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent


import com.google.android.gms.wearable.CapabilityClient
import com.google.android.gms.wearable.Wearable
import com.gunpang.watch_ui.WatchMain

class MainActivity : ComponentActivity() {
    companion object {
        private const val PERMISSION_CHECK = 100
    }

    private lateinit var capabilityClient: CapabilityClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 필수 권한 부여
        //grantPermissions()

        // 모바일 - 워치 연결
        capabilityClient = Wearable.getCapabilityClient(this)

        // 화면 켜짐 유지
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContent {
            WatchMain()
        }
    }
    private fun grantPermissions() {
        TODO("필요한 권한 확인후, list에 넣을 것")
        //val needPermissions = listOf(Manifest.permission.HEART_BEAT)
        //ActivityCompat.requestPermissions(this,needPermissions,PERMISSION_CHECK);
    }
}
