package com.itechart.crushon.repository

import com.itechart.crushon.model.User
import org.hibernate.Criteria
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Long> {
    fun findUserByUsername(username: String): User?
}
