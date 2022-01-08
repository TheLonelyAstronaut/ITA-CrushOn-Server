package com.itechart.crushon.service

import com.itechart.crushon.dto.authentication.AuthenticationOutputDTO

interface AuthenticationService {
    fun authenticate(username: String, password: String): AuthenticationOutputDTO
    fun refreshTokens(refreshToken: String): AuthenticationOutputDTO
}
