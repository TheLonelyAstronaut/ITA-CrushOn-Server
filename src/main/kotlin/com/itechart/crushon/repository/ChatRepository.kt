package com.itechart.crushon.repository

import com.itechart.crushon.model.Chat
import com.itechart.crushon.model.User
import org.springframework.data.repository.CrudRepository

interface ChatRepository: CrudRepository<Chat, Long> {
    fun findByFirstUser(user: User): Iterable<Chat>
    fun findBySecondUser(user: User): Iterable<Chat>
    fun findByFirstUserAndSecondUser(first: User, second: User): Chat?
}
