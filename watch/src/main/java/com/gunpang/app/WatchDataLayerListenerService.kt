package com.gunpang.app

import android.content.Intent
import android.util.Log
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService
import com.gunpang.data.repository.DataApplicationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

class WatchDataLayerListenerService : WearableListenerService() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private val dataApplicationRepository: DataApplicationRepository = DataApplicationRepository()

    override fun onMessageReceived(messageEvent: MessageEvent) {
        super.onMessageReceived(messageEvent)
        when (messageEvent.path) {
            START_WEAR_ACTIVITY_PATH -> {
                // 모바일 기기와 연동시 playId 저장 후 앱 실행
                val playerId = messageEvent.data.decodeToString()
                Log.d("테스트", "리스너 : $playerId")
                dataApplicationRepository.setValue("accessToken", "")
                dataApplicationRepository.setValue("refreshToken", "")
                dataApplicationRepository.setValue("playerId", playerId)
                startActivity(
                    Intent(this, MainActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    companion object {
        private const val START_WEAR_ACTIVITY_PATH = "/start-activity"
    }
}
