package com.itechart.crushon.dto.authentication

data class AuthenticationOutputDTO(
    val authorizationToken: String,
    val refreshToken: String,
)
