package com.gunpang.data.service

import android.annotation.SuppressLint
import android.app.Service
import android.util.Log
import androidx.health.services.client.data.DataType
import androidx.health.services.client.data.ExerciseUpdate
import com.google.gson.Gson
import com.gunpang.data.DataApplication
import com.gunpang.data.manager.ExerciseClientManager
import com.gunpang.data.manager.ExerciseMessage
import com.gunpang.data.repository.DataApplicationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.LifecycleEvent
import java.time.LocalDateTime
import javax.inject.Inject
class ExerciseServiceMonitor @Inject constructor(
    private val exerciseClientManager: ExerciseClientManager,
    val service: Service
) {
    // TODO behind an interface
    private val exerciseService = service as ExerciseService

    val exerciseServiceState = MutableStateFlow(
        ExerciseServiceState(
            exerciseState = null,
            exerciseMetrics = ExerciseMetrics()
        )
    )
    // Heart Data Socket Send
    private val GUNPANG_SOCKET_URL = "ws://10.0.2.2:8180/live/bigdata/watch-data-to-server/websocket"
//    private val client = OkHttpClient()
//    private lateinit var ws: WebSocket
    private lateinit var stompClient: StompClient
    private lateinit var playerId:String
    init{
        initializeWebSocket()
        initPlayerId()
    }

    private fun initPlayerId() {
        playerId = DataApplicationRepository().getValue("playerId")
    }

    suspend fun monitor() {
        exerciseClientManager.exerciseUpdateFlow.collect {
            when (it) {
                is ExerciseMessage.ExerciseUpdateMessage ->
                    processExerciseUpdate(it.exerciseUpdate)

                is ExerciseMessage.LapSummaryMessage ->
                    exerciseServiceState.update { oldState ->
                        oldState.copy(
                            exerciseLaps = it.lapSummary.lapCount
                        )
                    }

                is ExerciseMessage.LocationAvailabilityMessage ->
                    exerciseServiceState.update { oldState ->
                        oldState.copy(
                            locationAvailability = it.locationAvailability
                        )
                    }
            }
        }
    }

    @SuppressLint("RestrictedApi")
    private fun processExerciseUpdate(exerciseUpdate: ExerciseUpdate) {
        // Dismiss any ongoing activity notification.
        if (exerciseUpdate.exerciseStateInfo.state.isEnded) {
            exerciseService.removeOngoingActivityNotification()
        }

        exerciseServiceState.update { old ->
            old.copy(
                exerciseState = exerciseUpdate.exerciseStateInfo.state,
                exerciseMetrics = old.exerciseMetrics.update(exerciseUpdate.latestMetrics),
                activeDurationCheckpoint = exerciseUpdate.activeDurationCheckpoint
                    ?: old.activeDurationCheckpoint
            )
        }

        val heartRate = exerciseUpdate.latestMetrics.getData(DataType.HEART_RATE_BPM).lastOrNull()?.value
        if (heartRate != null) {
            sendHeartRate(heartRate)
        }
    }

    private fun initializeWebSocket(){
//        val request = Request.Builder().url(GUNPANG_SOCKET_URL).build()
//        val listener = HeartRateWebSocketListener()
//        ws = client.newWebSocket(request, listener)
        stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, GUNPANG_SOCKET_URL)
        // Stomp 연결 상태를 관리하는 리스너를 설정
        stompClient.lifecycle().subscribe { event ->
            when (event.type) {
                LifecycleEvent.Type.OPENED -> {
                    println("Stomp connection opened")
                    // TODO: 연결이 열린 후에 메시지를 보내거나 구독을 시작
                }
                LifecycleEvent.Type.ERROR -> {
                    println("Error"+ event.exception)
                }
                LifecycleEvent.Type.CLOSED -> {
                    println("Stomp connection closed")
                }
                else -> {}
            }
        }
        // 연결을 시작
        stompClient.connect()

        stompClient.topic("/sub/heartbeat").subscribe({ topicMessage ->
            // 메시지 처리
            println("Received: ${topicMessage.payload}")
            val heartbeat = Gson().fromJson(topicMessage.payload, Heartbeat::class.java)
            Log.d("HEARTBEAT",heartbeat.toString());
        }, { throwable ->
            // 에러 처리
            println("Error on subscribe topic$throwable")
        })
    }
    private fun sendHeartRate(heartRate: Double) {
        //ws.send(heartRate.toString())
        if (stompClient.isConnected) { // 연결 상태 체크
            //val playerId = "0" // TODO: 로그인 성공시 넣기
            val heartbeat = Heartbeat(playerId, heartRate,LocalDateTime.now().toString())
            val jsonHeartbeat = Gson().toJson(heartbeat)
            stompClient.send("/topic/heartbeat", jsonHeartbeat).subscribe({
                // TODO:메시지 전송 성공
            }, { throwable ->
                // 에러 처리
                println(throwable)
            })
        } else {
            println("Stomp client is not connected")
            // 연결 시도, 에러 처리, 또는 재시도 로직
            stompClient.reconnect()
        }
    }
    fun closeWebSocket() {
        //ws.close(1000, "Goodbye, World!")
        if (stompClient.isConnected) {
            stompClient.disconnect()
        }
    }
}
data class Heartbeat(val playerId: String, val heartbeat: Double, val createdAt: String)
