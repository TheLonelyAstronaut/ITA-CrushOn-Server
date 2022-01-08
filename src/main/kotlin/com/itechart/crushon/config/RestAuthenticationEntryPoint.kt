package com.itechart.crushon.config

import kotlinx.coroutines.reactor.mono
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.server.ServerAuthenticationEntryPoint
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class RestAuthenticationEntryPoint : ServerAuthenticationEntryPoint {
    override fun commence(exchange: ServerWebExchange?, ex: AuthenticationException?): Mono<Void>  = mono {
        ex?.let {
            exchange?.response?.statusCode = HttpStatus.UNAUTHORIZED
        }

        return@mono null
    }
}
