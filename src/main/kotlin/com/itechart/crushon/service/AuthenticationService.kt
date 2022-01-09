package com.itechart.crushon.service

import com.itechart.crushon.dto.authentication.AuthenticationOutputDTO

interface AuthenticationService {
    fun authenticate(username: String, password: String): AuthenticationOutputDTO
    fun createAuthenticationRecord(username: String, password: String)
    fun refreshTokens(refreshToken: String): AuthenticationOutputDTO
}
