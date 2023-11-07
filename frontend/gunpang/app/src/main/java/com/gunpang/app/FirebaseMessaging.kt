package com.gunpang.app

import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.gunpang.data.repository.DataApplicationRepository
import com.gunpang.domain.app.landing.NotificationViewModel

class FirebaseMessaging: FirebaseMessagingService() {

    private val notificationViewModel = NotificationViewModel()

    // 새로운 token이 생성될 때마다 호출되는 callback
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("onNewToken", "$token")

        // 토큰 저장
        DataApplicationRepository().setValue("fcmToken", token)
        if (DataApplicationRepository().getValue("accessToken").isNotEmpty()) { // 이미 로그인이 된 상태라면 서버에 토큰을 전송
            notificationViewModel.registerFCMTokens()
        }
    }

    // 새로운 메시지가 도착했을 때 호출되는 callback
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("FirebaseMessaging", "$message")

        // 알림 내용 확인하기
        Log.d("FirebaseMessaging", "Notification Title :: ${message.notification?.title}")
        Log.d("FirebaseMessaging", "Notification Body :: ${message.notification?.body}")
        Log.d("FirebaseMessaging", "Notification Data :: ${message.data}")

        // 알림 전송하기
        val channel = NotificationCompat.Builder(this, "@string/default_notification_channel_id")
            .setContentTitle(message.notification?.title)
            .setContentText(message.notification?.body)
            .setSmallIcon(com.gunpang.common.R.drawable.logo)
            .build()

        val managerCompat = NotificationManagerCompat.from(this)
        managerCompat.notify(1001, channel)
    }


}