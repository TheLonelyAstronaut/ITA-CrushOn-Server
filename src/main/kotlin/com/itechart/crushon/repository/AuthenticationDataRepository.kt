package com.itechart.crushon.repository

import com.itechart.crushon.model.AuthenticationData
import org.springframework.data.repository.CrudRepository

interface AuthenticationDataRepository: CrudRepository<AuthenticationData, Long> {
    fun findAuthenticationDataByUsername(username: String): AuthenticationData?
}
