package com.itechart.crushon.utils.socket

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketMessage
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Mono

@Component
class SocketConnectionPool {
    private val pool = mutableMapOf<Long, WebSocketSession>()

    fun addToPool(id: Long, session: WebSocketSession) {
        pool[id] = session
    }

    fun removeFromPool(id: Long) {
        pool.remove(id)
    }

    fun send(id: Long, data: String) =
        runBlocking {
            withContext(Dispatchers.IO) {
                pool[id]?.send(Mono.just(pool[id]!!.textMessage(data)))?.block()
            }
        }
}
