package com.itechart.crushon.utils.token

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws

interface TokenProvider {
    fun createAuthorizationToken(username: String): String
    fun createRefreshToken(username: String): String
    fun validateToken(token: String): Jws<Claims>
    fun isTokenValid(token: String): Boolean
    fun getUsernameFromToken(token: String): String
}
