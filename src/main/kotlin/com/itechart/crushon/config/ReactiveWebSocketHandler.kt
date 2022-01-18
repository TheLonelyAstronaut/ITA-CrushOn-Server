package com.itechart.crushon.config

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.reactor.flux
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketMessage
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Component
class ReactiveWebSocketHandler: WebSocketHandler {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun handle(session: WebSocketSession): Mono<Void> {
        session
            .receive()
            .collectMap {
                println(it.payloadAsText)
            }

        val messages: Flux<WebSocketMessage> = flux {
            send(session.textMessage("Bruh"))
        }

        return session.send(messages)
    }
}
