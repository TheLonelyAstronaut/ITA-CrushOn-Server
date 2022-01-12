package com.itechart.crushon.config

import com.itechart.crushon.security.JWTAuthenticationConverter
import com.itechart.crushon.security.JWTAuthenticationManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter

@Configuration
@EnableWebFluxSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SpringSecurityConfig(
    private val jwtAuthenticationManager: JWTAuthenticationManager,
    private val jwtAuthenticationConverter: JWTAuthenticationConverter,
    private val entryPoint: RestAuthenticationEntryPoint
) {
    private val authenticationEndpoint: String = "/api/v1/auth/**"
    private val passionsEndpoint: String = "/api/v1/explore/passions"
    private val citiesEndpoint: String = "/api/v1/explore/cities"

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        val authenticationWebFilter = AuthenticationWebFilter(jwtAuthenticationManager)
        authenticationWebFilter.setServerAuthenticationConverter(jwtAuthenticationConverter)

        return http
            .authorizeExchange()
            .pathMatchers(authenticationEndpoint, passionsEndpoint, citiesEndpoint)
            .permitAll()
            .anyExchange()
            .authenticated()
            .and()
            .addFilterBefore(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .exceptionHandling()
            .authenticationEntryPoint(entryPoint)
            .and()
            .cors()
            .and()
            .httpBasic()
            .disable()
            .csrf()
            .disable()
            .build()
    }
}
