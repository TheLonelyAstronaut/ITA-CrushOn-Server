package com.itechart.crushon.security.impl

import com.itechart.crushon.security.JWTAuthenticationConverter
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.reactor.mono
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class JWTAuthenticationConverterImpl: JWTAuthenticationConverter {
    override fun convert(exchange: ServerWebExchange?): Mono<Authentication> = mono {
        flowOf(exchange)
            .map { it?.request?.headers?.get("Authorization") }
            .map { it?.get(0) }
            .filter { it != null }
            .map { UsernamePasswordAuthenticationToken(it, it) }
            .firstOrNull()
    }
}
