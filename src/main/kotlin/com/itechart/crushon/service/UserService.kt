package com.itechart.crushon.service

import com.itechart.crushon.dto.user.CreateUserInputDTO
import com.itechart.crushon.model.User

interface UserService {
    fun createUser(data: CreateUserInputDTO): User
}
