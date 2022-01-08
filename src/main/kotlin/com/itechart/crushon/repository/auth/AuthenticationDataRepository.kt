package com.itechart.crushon.repository.auth

import com.itechart.crushon.model.auth.AuthenticationData
import org.springframework.data.repository.CrudRepository

interface AuthenticationDataRepository: CrudRepository<AuthenticationData, Long> {
    fun findAuthenticationDataByUsername(username: String): AuthenticationData?
}
