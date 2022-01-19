package com.itechart.crushon.repository

import com.itechart.crushon.model.PushToken
import com.itechart.crushon.model.User
import org.springframework.data.repository.CrudRepository

interface PushTokenRepository: CrudRepository<PushToken, Long> {
    fun findByOwner(owner: User): Iterable<PushToken>
    fun findByOwnerAndToken(owner: User, token: String): PushToken?
}
