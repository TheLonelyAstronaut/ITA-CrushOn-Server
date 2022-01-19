package com.itechart.crushon.utils.socket

import com.itechart.crushon.repository.UserRepository
import com.itechart.crushon.service.ChatService
import com.itechart.crushon.utils.token.TokenProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.reactor.mono
import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.CloseStatus
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono

@Component
class ReactiveWebSocketHandler(
    private val tokenProvider: TokenProvider,
    private val userRepository: UserRepository,
    private val pool: SocketConnectionPool
): WebSocketHandler {

    override fun handle(session: WebSocketSession): Mono<Void> = mono {
        val token = UriComponentsBuilder.fromUri(session.handshakeInfo.uri).build().queryParams["token"]?.get(0)

        token?.let {
            if(tokenProvider.isTokenValid(it)) {
                val username = tokenProvider.getUsernameFromToken(it)
                val user = userRepository.findUserByUsername(username)!!

                pool.addToPool(user.id!!, session)

                val response = session
                    .receive()
                    .flatMap {
                        Mono.just(session.textMessage("Socket is used only for client notification, no commands should be passed to server by this connection"))
                    }
                    .doOnTerminate {
                        pool.removeFromPool(user.id!!)
                    }

                session.send(response).block()
            } else {
                session.close(CloseStatus.NOT_ACCEPTABLE)
            }
        }

        Unit as Void
    }
}
