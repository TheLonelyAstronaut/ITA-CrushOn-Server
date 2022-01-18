package com.itechart.crushon.service.impl

import com.itechart.crushon.dto.authentication.AuthenticationOutputDTO
import com.itechart.crushon.model.AuthenticationData
import com.itechart.crushon.repository.AuthenticationDataRepository
import com.itechart.crushon.service.AuthenticationService
import com.itechart.crushon.utils.hasher.HashEvaluator
import com.itechart.crushon.utils.token.TokenProvider
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
    private val tokenProvider: TokenProvider,
    private val authenticationDataRepository: AuthenticationDataRepository,
    private val hashEvaluator: HashEvaluator
): AuthenticationService {

    override fun authenticate(username: String, password: String): AuthenticationOutputDTO {
        val authData = authenticationDataRepository.findAuthenticationDataByUsername(username)

        authData?.let {
            if(hashEvaluator.matches(password, authData.passwordHash)) {
                return AuthenticationOutputDTO(
                    authorizationToken = tokenProvider.createAuthorizationToken(username),
                    refreshToken = tokenProvider.createRefreshToken(username),
                )
            }
        }

        throw Exception("Not authorized")
    }

    override fun createAuthenticationRecord(username: String, password: String) {
        val data = AuthenticationData(username, hashEvaluator.encode(password))

        authenticationDataRepository.save(data)
    }

    override fun refreshTokens(refreshToken: String): AuthenticationOutputDTO {
        if(tokenProvider.isTokenValid(refreshToken)) {
            val username = tokenProvider.getUsernameFromToken(refreshToken)

            return AuthenticationOutputDTO(
                authorizationToken = tokenProvider.createAuthorizationToken(username),
                refreshToken = tokenProvider.createRefreshToken(username)
            )
        } else {
            throw Exception("Incorrect refresh token")
        }
    }
}
