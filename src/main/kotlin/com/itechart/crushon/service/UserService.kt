package com.itechart.crushon.service

import com.itechart.crushon.dto.user.CreateUserInputDTO
import com.itechart.crushon.dto.user.UpdateUserDTO
import com.itechart.crushon.model.Match
import com.itechart.crushon.model.User
import kotlinx.coroutines.flow.Flow

interface UserService {
    fun createUser(data: CreateUserInputDTO): User
    fun setPhoto(user: User, photoId: Long): Long
    fun updateUser(user: User, data: UpdateUserDTO): User
    fun getMatches(user: User): Flow<Match>
}
