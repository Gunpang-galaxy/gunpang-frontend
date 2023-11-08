package com.gunpang.data.service

import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class HeartRateWebSocketListener  : WebSocketListener() {
    override fun onOpen(webSocket: WebSocket, response: okhttp3.Response) {
        // WebSocket이 열리면 호출됩니다.
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        // 서버로부터 텍스트 메시지를 받으면 호출됩니다.
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        // 서버로부터 바이너리 메시지를 받으면 호출됩니다.
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        // 서버가 연결을 종료할 준비를 하면 호출됩니다.
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        // 연결이 종료되면 호출됩니다.
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: okhttp3.Response?) {
        // 오류가 발생하면 호출됩니다.
    }
}

