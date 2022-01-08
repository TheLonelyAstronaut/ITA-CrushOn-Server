package com.itechart.crushon.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SpringSecurityConfig(
    //private val jwtConfigurer: JWTConfigurer
) {
    private val authenticationEndpoint: String = "/api/v1/auth/**"

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .cors()
            .and()
            .httpBasic()
            .disable()
            .csrf()
            .disable()
            .authorizeExchange()
            .pathMatchers(authenticationEndpoint)
            .permitAll()
            .anyExchange()
            .authenticated()
            .and()
            .build()
    }
}
