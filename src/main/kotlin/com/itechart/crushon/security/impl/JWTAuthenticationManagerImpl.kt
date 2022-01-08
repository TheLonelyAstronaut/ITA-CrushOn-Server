package com.itechart.crushon.security.impl

import com.itechart.crushon.security.JWTAuthenticationManager
import com.itechart.crushon.utils.TokenProvider
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.reactor.mono
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class JWTAuthenticationManagerImpl(
    private val tokenProvider: TokenProvider
): JWTAuthenticationManager {
    override fun authenticate(authentication: Authentication?): Mono<Authentication> = mono {
        flowOf(authentication)
            .filter { it != null }
            .map { tokenProvider.validateToken(it!!.credentials as String) }
            .map {
                val up = UsernamePasswordAuthenticationToken(
                    it.body,
                    authentication!!.credentials as String,
                    mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
                )

                val sc = SecurityContextHolder.getContext()
                sc.authentication = up

                up
            }
            .first()
    }
}
